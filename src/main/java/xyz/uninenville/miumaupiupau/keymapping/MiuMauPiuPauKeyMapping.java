package xyz.uninenville.miumaupiupau.keymapping;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.util.RandomSource;
import org.lwjgl.glfw.GLFW;
import xyz.uninenville.miumaupiupau.MiuMauPiuPau;

import java.util.ArrayList;
import java.util.List;

public class MiuMauPiuPauKeyMapping extends KeyMapping {

    public MiuMauPiuPauKeyMapping() {
        super("key.miumaupiupau.miumaupiupau", GLFW.GLFW_KEY_UNKNOWN, KeyMapping.Category.register(MiuMauPiuPau.id(MiuMauPiuPau.MOD_ID)));

        ClientTickEvents.END_CLIENT_TICK.register(this::tick);
    }

    public void tick(Minecraft client) {
        if (this.consumeClick()) {
            List<String> words = new ArrayList<>(MiuMauPiuPau.getConfig().words());
            RandomSource random = RandomSource.create();

            if (words.isEmpty()) {
                return;
            } else if (words.size() > 1) {
                words.remove(MiuMauPiuPau.getPreviousWord());
            }

            LocalPlayer player = Minecraft.getInstance().player;
            if (player != null) {
                MiuMauPiuPau.setPreviousWord(words.get(random.nextInt(words.size())));
                Minecraft.getInstance().player.connection.sendChat(MiuMauPiuPau.getPreviousWord());
            }
            this.release();
        }
    }

}
