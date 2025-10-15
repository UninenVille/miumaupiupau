package xyz.uninenville.miumaupiupau.keybinding;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.util.math.random.Random;
import org.lwjgl.glfw.GLFW;
import xyz.uninenville.miumaupiupau.MiuMauPiuPau;

import java.util.ArrayList;
import java.util.List;

public class MiuMauPiuPauKeybinding extends KeyBinding {

    public MiuMauPiuPauKeybinding() {
        super("key.miumaupiupau.miumaupiupau", GLFW.GLFW_KEY_UNKNOWN, KeyBinding.Category.create(MiuMauPiuPau.id(MiuMauPiuPau.MOD_ID)));

        ClientTickEvents.END_CLIENT_TICK.register(this::tick);
    }

    public void tick(MinecraftClient client) {
        if (this.wasPressed()) {
            List<String> words = new ArrayList<>(MiuMauPiuPau.getConfig().words());
            Random random = Random.create();

            if (words.isEmpty()) {
                return;
            } else if (words.size() > 1) {
                words.remove(MiuMauPiuPau.getPreviousWord());
            }

            ClientPlayerEntity player = MinecraftClient.getInstance().player;
            if (player != null) {
                MiuMauPiuPau.setPreviousWord(words.get(random.nextInt(words.size())));
                MinecraftClient.getInstance().player.networkHandler.sendChatMessage(MiuMauPiuPau.getPreviousWord());
            }
            this.reset();
        }
    }

}
