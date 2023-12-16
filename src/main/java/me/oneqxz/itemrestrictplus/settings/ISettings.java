package me.oneqxz.itemrestrictplus.settings;

import com.google.common.base.Charsets;
import me.oneqxz.itemrestrictplus.ItemRestrictPlus;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;

public interface ISettings {
    void reload() throws ReloadException;
    default YamlConfiguration getConfig(String configFile)
    {
        YamlConfiguration newConfig = YamlConfiguration.loadConfiguration(new File(ItemRestrictPlus.api.getDataFolder(), configFile));
        InputStream defConfigStream = ItemRestrictPlus.api.getResource(configFile);
        if (defConfigStream != null) {
            newConfig.setDefaults(YamlConfiguration.loadConfiguration(new InputStreamReader(defConfigStream, Charsets.UTF_8)));
        }

        return newConfig;
    }

    default void saveConfig(String configFile)
    {
        if(!getConfigFile(configFile).exists())
            ItemRestrictPlus.api.saveResource(configFile, false);
    }

    default File getConfigFile(String configFile)
    {
        return new File(ItemRestrictPlus.api.getDataFolder(), configFile);
    }
}
