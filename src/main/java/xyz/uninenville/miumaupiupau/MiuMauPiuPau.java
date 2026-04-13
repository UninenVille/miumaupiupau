package xyz.uninenville.miumaupiupau;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.fabricmc.fabric.api.client.message.v1.ClientReceiveMessageEvents;
import net.fabricmc.fabric.api.client.message.v1.ClientSendMessageEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.RandomSource;
import net.minecraft.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.uninenville.ducktape.api.config.ConfigHolder;
import xyz.uninenville.miumaupiupau.config.Config;
import xyz.uninenville.miumaupiupau.keymapping.MiuMauPiuPauKeyMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MiuMauPiuPau implements ModInitializer {
    public static final String MOD_ID = "miumaupiupau";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static ConfigHolder CONFIG_HOLDER = new ConfigHolder(FabricLoader.getInstance().getConfigDir().resolve(MOD_ID + ".json"), new Config());

    private static String previousWord = "";

    @Override
    public void onInitialize() {
        LOGGER.info("Hello Itäkeskus and ᓚ₍^. .^₎ wörld!");
        ClientSendMessageEvents.MODIFY_CHAT.register(MiuMauPiuPau::modifyChatMessage);
        ClientSendMessageEvents.MODIFY_COMMAND.register(MiuMauPiuPau::modifyCommandMessage);
        ClientReceiveMessageEvents.CHAT.register((message, signedMessage, sender, params, receptionTimestamp) -> MiuMauPiuPau.onChat(message.getString()));
        ClientReceiveMessageEvents.GAME.register((message, overlay) -> MiuMauPiuPau.onChat(message.getString()));
        KeyMappingHelper.registerKeyMapping(new MiuMauPiuPauKeyMapping());
    }

    private static String modifyChatMessage(String message) {
        Config config = MiuMauPiuPau.getConfig();

        if (config.prefix().isEmpty() || message.startsWith(config.prefix())) {
            RandomSource random = RandomSource.create();
            List<String> words = new ArrayList<>(config.words());
            message = message.substring(config.prefix().length());

            if (words.isEmpty()) {
                return message;
            } else if (words.size() > 1) {
                words.remove(previousWord);
            }

            StringBuilder newMessage = new StringBuilder();
            for (String word : message.split(" ")) {
                newMessage.append(word).append(" ");

                if ((random.nextInt(100) <= config.chance() || message.isEmpty()) && !words.isEmpty()) {
                    setPreviousWord(words.get(random.nextInt(words.size())));
                    newMessage.append(previousWord).append(" ");

                    if (!config.allowDuplicateWords()) {
                        words.remove(previousWord);
                    }
                }
            }

            return StringUtil.trimChatMessage(StringUtils.normalizeSpace(newMessage.toString().trim()));
        }

        return message;
    }

    private static void onChat(String message) {
        Config config = MiuMauPiuPau.getConfig();

        if (config.catSoundsOnMeowsInChat()) {
            for (String word : config.words()) {
                if (message.contains(word)) {
                    LocalPlayer player = Minecraft.getInstance().player;
                    if (player != null) {
                        player.playSound(SoundEvent.createVariableRangeEvent(Identifier.parse(config.catSound())), config.catSoundVolume(), 1F);
                    }
                    break;
                }
            }
        }
    }

    private static String modifyCommandMessage(String command) {
        List<String> commandWhitelist = MiuMauPiuPau.getConfig().commandWhitelist();
        if (!commandWhitelist.isEmpty()) {
            for (String regex : commandWhitelist) {
                Matcher matcher = Pattern.compile(regex.strip() + " ").matcher(command);

                if (matcher.find()) {
                    command = matcher.group() + modifyChatMessage(command.replaceFirst(regex, "").strip());
                    break;
                }
            }
        }

        return command;
    }

    public static String getPreviousWord() {
        return previousWord;
    }

    public static void setPreviousWord(String word) {
        MiuMauPiuPau.previousWord = word;
    }

    public static Config getConfig() {
        return CONFIG_HOLDER.getConfig();
    }

    public static Identifier id(String path) {
        return Identifier.fromNamespaceAndPath(MOD_ID, path);
    }
}