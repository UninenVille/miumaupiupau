package uninenville.miumaupiupau.config;

import uninenville.ducktape.api.config.AbstractConfig;

import java.util.List;

public class Config extends AbstractConfig {
    String prefix = "!";
    int chance = 50;
    boolean duplicateWords = false;
    List<String> commandWhitelist = List.of(
        "^msg [a-zA-Z0-9_]{2,16} ", "^tell [a-zA-Z0-9_]{2,16} ", "^w [a-zA-Z0-9_]{2,16} ", "^reply ", "^r ", "^me "
    );
    List<String> words = List.of(
        "miumaupiupau", "miumau", "miu", "mau", "meow", "purr",
        "ᓚ₍^. .^₎", "₍^. .^₎ᓗ", "⟅₍^. .^₎", "₍^. .^₎⟆", "ᓚᕠᗢ", "ᗢᕡᓗ",
        "ʕ•ᴥ•ʔ", "kvaak", ":3"
    );

    public String getPrefix() {
        return prefix;
    }

    public int getChance() {
        return chance;
    }

    public boolean allowDuplicateWords() {
        return duplicateWords;
    }

    public List<String> getCommandWhitelist() {
        return commandWhitelist;
    }

    public List<String> getWords() {
        return words;
    }
}
