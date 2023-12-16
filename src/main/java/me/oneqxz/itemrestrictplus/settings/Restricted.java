package me.oneqxz.itemrestrictplus.settings;

import lombok.extern.log4j.Log4j2;
import org.bukkit.configuration.file.YamlConfiguration;

@Log4j2
public class Restricted implements ISettings{

    private static Restricted instance;
    private YamlConfiguration config;

    private Restricted()
    {
        try
        {
            reload();
        }
        catch (Throwable e)
        {
            log.error("Can't reload a restricted.yml", e);
        }
    }

    @Override
    public void reload() throws ReloadException {
        this.config = getConfig("restricted.yml");
        saveConfig("restricted.yml");
    }

    public static Restricted getInstance() {
        return instance == null ? instance = new Restricted() : instance;
    }
}
