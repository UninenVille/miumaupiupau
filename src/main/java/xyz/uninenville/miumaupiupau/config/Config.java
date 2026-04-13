package xyz.uninenville.miumaupiupau.config;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.animal.feline.CatSoundVariants;

import java.util.List;

public class Config {
    private String prefix = "!";
    private int chance = 50;
    private boolean allowDuplicateWords = false;
    private List<String> commandWhitelist = List.of(
        "^(msg|tell|w) [a-zA-Z0-9_]{2,16}", "^(reply|r)", "^me", "^(partychat|pc)"
    );
    private List<String> words = List.of(
        "miumaupiupau", "miumau", "miu", "mau", "meow", "purr",
        "ᓚ₍^. .^₎", "₍^. .^₎ᓗ", "⟅₍^. .^₎", "₍^. .^₎⟆", "ᓚᕠᗢ", "ᗢᕡᓗ", "🐈",
        "ʕ•ᴥ•ʔ", "kvaak", ":3", "mäy", "૮₍˶• ༝ •˶₎ა", "ᓚ₍⑅^- .-^₎ -ᶻ 𝗓 𐰁"
    );
    private boolean catSoundsOnMeowsInChat = true;
    private float catSoundVolume = 0.5F;
    private String catSound = SoundEvents.CAT_SOUNDS.get(CatSoundVariants.SoundSet.CLASSIC).adultSounds().ambientSound().value().location().toString();

    public String prefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public int chance() {
        return chance;
    }

    public void setChance(int chance) {
        this.chance = chance;
    }

    public boolean allowDuplicateWords() {
        return allowDuplicateWords;
    }

    public void setAllowDuplicateWords(boolean allowDuplicateWords) {
        this.allowDuplicateWords = allowDuplicateWords;
    }

    public List<String> commandWhitelist() {
        return commandWhitelist;
    }

    public void setCommandWhitelist(List<String> commandWhitelist) {
        this.commandWhitelist = commandWhitelist;
    }

    public List<String> words() {
        return words;
    }

    public void setWords(List<String> words) {
        this.words = words;
    }

    public boolean catSoundsOnMeowsInChat() {
        return catSoundsOnMeowsInChat;
    }

    public void setCatSoundsOnMeowsInChat(boolean catSoundsOnMeowsInChat) {
        this.catSoundsOnMeowsInChat = catSoundsOnMeowsInChat;
    }

    public float catSoundVolume() {
        return catSoundVolume;
    }

    public void setCatSoundVolume(float soundVolume) {
        this.catSoundVolume = soundVolume;
    }

    public String catSound() {
        return catSound;
    }

    public void setCatSound(String catSound) {
        this.catSound = catSound;
    }
}
