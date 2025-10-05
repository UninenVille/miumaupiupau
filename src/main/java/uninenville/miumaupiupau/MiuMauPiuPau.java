package uninenville.miumaupiupau;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.message.v1.ClientSendMessageEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.util.Identifier;
import net.minecraft.util.StringHelper;
import net.minecraft.util.math.random.Random;
import org.apache.commons.lang3.StringUtils;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uninenville.ducktape.api.config.AbstractConfig;
import uninenville.miumaupiupau.config.Config;
import uninenville.miumaupiupau.keybinding.MiuMauPiuPauKeybinding;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MiuMauPiuPau implements ModInitializer {
    public static final String MOD_ID = "miumaupiupau";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static Config CONFIG = AbstractConfig.loadConfig(FabricLoader.getInstance().getConfigDir().resolve(MOD_ID + ".json"), new Config());

    private static String previousWord = "";

    @Override
    public void onInitialize() {
        LOGGER.info("Hello Itäkeskus and ᓚ₍^. .^₎ wörld!");
        ClientSendMessageEvents.MODIFY_CHAT.register(MiuMauPiuPau::modifyChatMessage);
        ClientSendMessageEvents.MODIFY_COMMAND.register(MiuMauPiuPau::modifyCommandMessage);
        KeyBindingHelper.registerKeyBinding(new MiuMauPiuPauKeybinding(id(MOD_ID), GLFW.GLFW_KEY_UNKNOWN, KeyBinding.Category.create(id(MOD_ID))));
    }

    private static String modifyChatMessage(String message) {
        if (CONFIG.getPrefix().isEmpty() || message.startsWith(CONFIG.getPrefix())) {
            Random random = Random.create();
            List<String> words = new ArrayList<>(CONFIG.getWords());
            message = message.substring(CONFIG.getPrefix().length());

            if (words.isEmpty()) {
                return message;
            } else if (words.size() > 1) {
                words.remove(previousWord);
            }

            StringBuilder newMessage = new StringBuilder();
            for (String word : message.split(" ")) {
                newMessage.append(word).append(" ");

                if ((random.nextInt(100) <= CONFIG.getChance() || message.isEmpty()) && !words.isEmpty()) {
                    setPreviousWord(words.get(random.nextInt(words.size())));
                    newMessage.append(previousWord).append(" ");

                    if (!CONFIG.allowDuplicateWords()) {
                        words.remove(previousWord);
                    }
                }
            }

            return StringHelper.truncateChat(StringUtils.normalizeSpace(newMessage.toString().trim()));
        }

        return message;
    }

    private static String modifyCommandMessage(String command) {
        List<String> commandWhitelist = CONFIG.getCommandWhitelist();
        if (!commandWhitelist.isEmpty()) {
            for (String regex : commandWhitelist) {
                Matcher matcher = Pattern.compile(regex).matcher(command);

                if (matcher.find()) {
                    command = matcher.group() + modifyChatMessage(command.replaceFirst(regex, ""));
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

    public static Identifier id(String path) {
        return Identifier.of(MOD_ID, path);
    }
}