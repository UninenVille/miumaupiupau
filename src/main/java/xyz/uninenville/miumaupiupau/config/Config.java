package xyz.uninenville.miumaupiupau.config;

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
}
