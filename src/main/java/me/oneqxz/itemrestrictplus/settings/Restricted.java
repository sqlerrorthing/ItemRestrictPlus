package me.oneqxz.itemrestrictplus.settings;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import me.oneqxz.itemrestrictplus.settings.types.RestrictAction;
import me.oneqxz.itemrestrictplus.settings.types.RestrictionType;
import org.bukkit.Material;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Log4j2
public class Restricted implements ISettings{

    private static Restricted instance;
    private YamlConfiguration config;

    @Getter private Restrict[] restricts;

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

        List<Restrict> rest = new LinkedList<>();
        for(String sectionKey : this.config.getKeys(false))
        {
            Restrict.RestrictBuilder builder = Restrict.builder();
            ConfigurationSection section = this.config.getConfigurationSection(sectionKey);
            ConfigurationSection restrictionSection = section.getConfigurationSection("restriction");

            builder.name(section.getString("name", ""));
            List<Restrict.Restriction> restrictions = new LinkedList<>();
            for(String restrictKey : restrictionSection.getKeys(false))
            {
                restrictions.add(new Restrict.Restriction(
                        RestrictionType.valueOf(restrictKey.toUpperCase()),
                        new RestrictAction(
                                restrictionSection.getStringList(restrictKey).toArray(String[]::new)
                        )
                ));
            }

            builder.restrictions(restrictions.toArray(Restrict.Restriction[]::new));
            builder.items(section.getStringList("items").stream().map(str -> Material.valueOf(str.toUpperCase())).toArray(Material[]::new));

            rest.add(builder.build());
        }

        this.restricts = rest.toArray(Restrict[]::new);
    }

    @Getter
    @AllArgsConstructor
    @Builder
    public static class Restrict
    {
        private @NotNull String name;
        private @NotNull Restriction[] restrictions;
        private @NotNull Material[] items;

        @Getter
        @AllArgsConstructor
        public static class Restriction
        {
            private RestrictionType type;
            private RestrictAction action;
        }
    }

    public static Restricted getInstance() {
        return instance == null ? instance = new Restricted() : instance;
    }
}
