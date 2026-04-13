package xyz.uninenville.miumaupiupau.config;

import dev.isxander.yacl3.api.*;
import dev.isxander.yacl3.api.controller.FloatSliderControllerBuilder;
import dev.isxander.yacl3.api.controller.IntegerSliderControllerBuilder;
import dev.isxander.yacl3.api.controller.StringControllerBuilder;
import dev.isxander.yacl3.api.controller.TickBoxControllerBuilder;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import xyz.uninenville.miumaupiupau.MiuMauPiuPau;

public class YaclIntegration {
    public static Screen generateConfigScreen(Screen parent) {
        Config config = MiuMauPiuPau.getConfig();
        Config defaultConfig = new Config();

        return YetAnotherConfigLib.createBuilder()
            .title(Component.translatable("miumaupiupau.config.title"))
            .category(ConfigCategory.createBuilder()
                .name(Component.translatable("miumaupiupau.config.title"))
                .option(Option.<String>createBuilder()
                    .name(Component.translatable("miumaupiupau.config.prefix"))
                    .description(OptionDescription.of(Component.translatable("miumaupiupau.config.prefix.description")))
                    .binding(defaultConfig.prefix(), config::prefix, config::setPrefix)
                    .controller(StringControllerBuilder::create)
                    .build()
                )
                .option(Option.<Integer>createBuilder()
                    .name(Component.translatable("miumaupiupau.config.chance"))
                    .description(OptionDescription.of(Component.translatable("miumaupiupau.config.chance.description")))
                    .binding(defaultConfig.chance(), config::chance, config::setChance)
                    .controller(opt -> IntegerSliderControllerBuilder.create(opt).range(1, 100).step(1))
                    .build()
                )
                .option(Option.<Boolean>createBuilder()
                    .name(Component.translatable("miumaupiupau.config.duplicate_words"))
                    .description(OptionDescription.of(Component.translatable("miumaupiupau.config.duplicate_words.description")))
                    .binding(defaultConfig.allowDuplicateWords(), config::allowDuplicateWords, config::setAllowDuplicateWords)
                    .controller(TickBoxControllerBuilder::create)
                    .build()
                )
                .option(Option.<Boolean>createBuilder()
                    .name(Component.translatable("miumaupiupau.config.cat_sounds_on_meows_in_chat"))
                    .binding(defaultConfig.catSoundsOnMeowsInChat(), config::catSoundsOnMeowsInChat, config::setCatSoundsOnMeowsInChat)
                    .controller(TickBoxControllerBuilder::create)
                    .build()
                )
                .option(Option.<Float>createBuilder()
                    .name(Component.translatable("miumaupiupau.config.cat_sound_volume"))
                    .binding(defaultConfig.catSoundVolume(), config::catSoundVolume, config::setCatSoundVolume)
                    .controller(opt -> FloatSliderControllerBuilder.create(opt).range(0F, 1F).step(0.05F))
                    .build()
                )
                .option(Option.<String>createBuilder()
                    .name(Component.translatable("miumaupiupau.config.cat_sound"))
                    .binding(defaultConfig.catSound(), config::catSound, config::setCatSound)
                    .controller(StringControllerBuilder::create)
                    .build()
                )
                .group(ListOption.<String>createBuilder()
                    .name(Component.translatable("miumaupiupau.config.commandwhitelist"))
                    .description(OptionDescription.of(Component.translatable("miumaupiupau.config.commandwhitelist.description")))
                    .binding(defaultConfig.commandWhitelist(), config::commandWhitelist, config::setCommandWhitelist)
                    .controller(StringControllerBuilder::create)
                    .minimumNumberOfEntries(0)
                    .initial("")
                    .build()
                )
                .group(ListOption.<String>createBuilder()
                    .name(Component.translatable("miumaupiupau.config.words"))
                    .description(OptionDescription.of(Component.translatable("miumaupiupau.config.words.description")))
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
