package me.oneqxz.itemrestrictplus.settings;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import me.oneqxz.itemrestrictplus.settings.types.Restriction;
import org.bukkit.configuration.file.YamlConfiguration;

@Log4j2
public class Config implements ISettings {

    private static Config instance;
    private YamlConfiguration config;

    @Getter private GeneralSettings generalSettings;
    @Getter private CommandSettings commandSettings;

    private Config() {
        try
        {
            reload();
        }
        catch (Throwable e)
        {
            log.error("Can't reload a config.yml", e);
        }
    }

    @Override
    public void reload() throws ReloadException {
        this.config = getConfig("config.yml");
        saveConfig("config.yml");

        this.generalSettings = new GeneralSettings(
            this.config.getStringList("general.world_blacklist").toArray(String[]::new),
            this.config.getStringList("general.restrictions").stream().map(name -> Restriction.valueOf(name.toUpperCase())).toArray(Restriction[]::new)
        );

        this.commandSettings = new CommandSettings(
                this.config.getString("command.noPermission"),
                this.config.getString("command.reload"),
                this.config.getString("command.help"),
                this.config.getString("command.unknownCommand")
        );
    }

    @Getter
    @AllArgsConstructor
    public static final class GeneralSettings
    {
        private String[] worldBlacklisted;
        private Restriction[] restrictions;
    }

    @Getter
    @AllArgsConstructor
    public static final class CommandSettings
    {
        private String noPermission;
        private String reload;
        private String help;
        private String unknownCommand;
    }

    public static Config getInstance() {
        return instance == null ? instance = new Config() : instance;
    }
}
