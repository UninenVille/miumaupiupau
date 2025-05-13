package uninenville.miumaupiupau.config;

import dev.isxander.yacl3.api.*;
import dev.isxander.yacl3.api.controller.IntegerSliderControllerBuilder;
import dev.isxander.yacl3.api.controller.StringControllerBuilder;
import dev.isxander.yacl3.api.controller.TickBoxControllerBuilder;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

import static uninenville.miumaupiupau.MiuMauPiuPau.CONFIG;

public class YaclIntegration {
    public static Screen generateConfigScreen(Screen parent) {
        Config defaultConfig = new Config();

        return YetAnotherConfigLib.createBuilder()
            .title(Text.translatable("miumaupiupau.config.title"))
            .category(ConfigCategory.createBuilder()
                .name(Text.translatable("miumaupiupau.config.title"))
                .option(Option.<String>createBuilder()
                    .name(Text.translatable("miumaupiupau.config.prefix"))
                    .description(OptionDescription.of(Text.translatable("miumaupiupau.config.prefix.description")))
                    .binding(defaultConfig.getPrefix(), CONFIG::getPrefix, val -> CONFIG.prefix = val)
                    .controller(StringControllerBuilder::create)
                    .build()
                )
                .option(Option.<Integer>createBuilder()
                    .name(Text.translatable("miumaupiupau.config.chance"))
                    .description(OptionDescription.of(Text.translatable("miumaupiupau.config.chance.description")))
                    .binding(defaultConfig.getChance(), CONFIG::getChance, val -> CONFIG.chance = val)
                    .controller(opt -> IntegerSliderControllerBuilder.create(opt).range(1, 100).step(1))
                    .build()
                )
                .option(Option.<Boolean>createBuilder()
                    .name(Text.translatable("miumaupiupau.config.duplicate_words"))
                    .description(OptionDescription.of(Text.translatable("miumaupiupau.config.duplicate_words.description")))
                    .binding(defaultConfig.allowDuplicateWords(), CONFIG::allowDuplicateWords, val -> CONFIG.duplicateWords = val)
                    .controller(TickBoxControllerBuilder::create)
                    .build()
                )
                .group(ListOption.<String>createBuilder()
                    .name(Text.translatable("miumaupiupau.config.commandwhitelist"))
                    .description(OptionDescription.of(Text.translatable("miumaupiupau.config.commandwhitelist.description")))
                    .binding(defaultConfig.getCommandWhitelist(), CONFIG::getCommandWhitelist, val -> CONFIG.commandWhitelist = val)
                    .controller(StringControllerBuilder::create)
                    .minimumNumberOfEntries(0)
                    .initial("")
                    .build()
                )
                .group(ListOption.<String>createBuilder()
                    .name(Text.translatable("miumaupiupau.config.words"))
                    .description(OptionDescription.of(Text.translatable("miumaupiupau.config.words.description")))
                    .binding(defaultConfig.getWords(), CONFIG::getWords, val -> CONFIG.words = val)
                    .controller(StringControllerBuilder::create)
                    .minimumNumberOfEntries(0)
                    .initial("")
                    .build()
                )
                .build()
            )
            .save(CONFIG::save)
            .build()
            .generateScreen(parent);
    }
}
