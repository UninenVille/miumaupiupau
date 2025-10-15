package xyz.uninenville.miumaupiupau.config;

import dev.isxander.yacl3.api.*;
import dev.isxander.yacl3.api.controller.IntegerSliderControllerBuilder;
import dev.isxander.yacl3.api.controller.StringControllerBuilder;
import dev.isxander.yacl3.api.controller.TickBoxControllerBuilder;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import xyz.uninenville.miumaupiupau.MiuMauPiuPau;

public class YaclIntegration {
    public static Screen generateConfigScreen(Screen parent) {
        Config config = MiuMauPiuPau.getConfig();
        Config defaultConfig = new Config();

        return YetAnotherConfigLib.createBuilder()
            .title(Text.translatable("miumaupiupau.config.title"))
            .category(ConfigCategory.createBuilder()
                .name(Text.translatable("miumaupiupau.config.title"))
                .option(Option.<String>createBuilder()
                    .name(Text.translatable("miumaupiupau.config.prefix"))
                    .description(OptionDescription.of(Text.translatable("miumaupiupau.config.prefix.description")))
                    .binding(defaultConfig.prefix(), config::prefix, config::setPrefix)
                    .controller(StringControllerBuilder::create)
                    .build()
                )
                .option(Option.<Integer>createBuilder()
                    .name(Text.translatable("miumaupiupau.config.chance"))
                    .description(OptionDescription.of(Text.translatable("miumaupiupau.config.chance.description")))
                    .binding(defaultConfig.chance(), config::chance, config::setChance)
                    .controller(opt -> IntegerSliderControllerBuilder.create(opt).range(1, 100).step(1))
                    .build()
                )
                .option(Option.<Boolean>createBuilder()
                    .name(Text.translatable("miumaupiupau.config.duplicate_words"))
                    .description(OptionDescription.of(Text.translatable("miumaupiupau.config.duplicate_words.description")))
                    .binding(defaultConfig.allowDuplicateWords(), config::allowDuplicateWords, config::setAllowDuplicateWords)
                    .controller(TickBoxControllerBuilder::create)
                    .build()
                )
                .group(ListOption.<String>createBuilder()
                    .name(Text.translatable("miumaupiupau.config.commandwhitelist"))
                    .description(OptionDescription.of(Text.translatable("miumaupiupau.config.commandwhitelist.description")))
                    .binding(defaultConfig.commandWhitelist(), config::commandWhitelist, config::setCommandWhitelist)
                    .controller(StringControllerBuilder::create)
                    .minimumNumberOfEntries(0)
                    .initial("")
                    .build()
                )
                .group(ListOption.<String>createBuilder()
                    .name(Text.translatable("miumaupiupau.config.words"))
                    .description(OptionDescription.of(Text.translatable("miumaupiupau.config.words.description")))
                    .binding(defaultConfig.words(), config::words, config::setWords)
                    .controller(StringControllerBuilder::create)
                    .minimumNumberOfEntries(0)
                    .initial("")
                    .build()
                )
                .build()
            )
            .save(MiuMauPiuPau.CONFIG_HOLDER::saveConfig)
            .build()
            .generateScreen(parent);
    }
}
