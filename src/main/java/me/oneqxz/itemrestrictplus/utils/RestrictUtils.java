package me.oneqxz.itemrestrictplus.utils;

import me.oneqxz.itemrestrictplus.settings.Config;
import me.oneqxz.itemrestrictplus.settings.Restricted;
import me.oneqxz.itemrestrictplus.settings.types.RestrictionType;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.Arrays;

public class RestrictUtils {

    public static boolean isEnabledGlobal(RestrictionType type, World world)
    {
        return Arrays.asList(Config.getInstance().getGeneralSettings().getRestrictionTypes()).contains(type) &&
                Arrays.stream(Config.getInstance().getGeneralSettings().getWorldBlacklisted()).noneMatch(s -> world.getName().equals(s));
    }

    public static boolean process(@Nullable Material material, @NotNull RestrictionType type, @Nullable World world)
    {
        if(!isEnabledGlobal(type, world))
            return false;

        if(material == null || material == Material.AIR)
            return false;

        for(Restricted.Restrict restricted : Restricted.getInstance().getRestricts())
        {
            for(Restricted.Restrict.Restriction restrictionType : restricted.getRestrictions())
            {
                if(restrictionType.getType() == type)
                {
                    for(Material mat : restricted.getItems())
                    {
                        if(material == mat)
                            return true;
                    }
                }
            }
        }

        return false;
    }

    public static boolean process(@Nullable ItemStack stack, @NotNull RestrictionType type, @NotNull Player player)
    {
        return process(stack == null ? Material.AIR : stack.getType(), type, player);
    }

    public static boolean process(@Nullable Material material, @NotNull RestrictionType type, @NotNull Player player)
    {
        if(!isEnabledGlobal(type, player.getWorld()))
            return false;

        if(material == null || material == Material.AIR)
            return false;

        if(player.hasPermission("me.oneqxz.itemrestrictplus.bypass"))
            return false;

        for(Restricted.Restrict restricted : Restricted.getInstance().getRestricts())
        {
            for(Restricted.Restrict.Restriction restrictionType : restricted.getRestrictions())
            {
                if(restrictionType.getType() == type)
                {
                    for(Material mat : restricted.getItems())
                    {
                        if(material == mat)
                        {
                            Restricted.Restrict.Restriction globalRest = Arrays.stream(restricted.getRestrictions()).filter(s -> s.getType() == RestrictionType.GLOBAL).findFirst().orElse(null);
                            if(globalRest != null)
                                globalRest.getAction().process(player);

                            restrictionType.getAction().process(player);
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }
}
