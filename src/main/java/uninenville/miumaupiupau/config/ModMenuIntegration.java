package uninenville.miumaupiupau.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import net.fabricmc.loader.api.FabricLoader;
import uninenville.ducktape.api.config.InstallYaclScreen;

public class ModMenuIntegration implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        if (FabricLoader.getInstance().isModLoaded("yet_another_config_lib_v3")) {
            return YaclIntegration::generateConfigScreen;
        } else {
            return InstallYaclScreen::new;
        }
    }
}
