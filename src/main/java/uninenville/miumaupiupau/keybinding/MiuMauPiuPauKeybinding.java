package uninenville.miumaupiupau.keybinding;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.random.Random;
import uninenville.ducktape.keybinding.ModKeyBinding;
import uninenville.miumaupiupau.MiuMauPiuPau;

import java.util.ArrayList;
import java.util.List;

import static uninenville.miumaupiupau.MiuMauPiuPau.CONFIG;

public class MiuMauPiuPauKeybinding extends ModKeyBinding {

    public MiuMauPiuPauKeybinding(Identifier id, int code, String category) {
        super(id, code, category);
    }

    @Override
    public void onPressed() {
        List<String> words = new ArrayList<>(CONFIG.getWords());
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
    }

}
