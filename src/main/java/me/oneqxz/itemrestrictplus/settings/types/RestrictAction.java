package me.oneqxz.itemrestrictplus.settings.types;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

@AllArgsConstructor
@Log4j2
public class RestrictAction {

    private String[] actions;

    public void process(Player player)
    {
        try
        {
            this._process(player);
        }
        catch (Throwable t)
        {
            t.printStackTrace();
            player.sendMessage("\247cAn error occurred during processing, please check the console output to fix it");
        }
    }

    private void _process(Player player) throws Throwable
    {
        for(String str : this.actions)
        {
            String[] args = str.split(" ");
            if(args.length > 0)
            {
                if(args[0].startsWith("[message]"))
                {
                    player.sendMessage(formatString(player, parseString(args, 1)));
                }
                else if(args[0].startsWith("[player]"))
                {
                    player.performCommand(formatString(player, parseString(args, 1)));
                }
                else if(args[0].startsWith("[console]"))
                {
                    Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), formatString(player, parseString(args, 1)));
                }
                else if(args[0].startsWith("[sound]"))
                {
                    Sound sound = Sound.valueOf(args[1]);
                    player.playSound(player, sound, Float.parseFloat(args[2]), Float.parseFloat(args[3]));
                }
            }
        }
    }

    private String parseString(String[] str, int startIndex)
    {
        StringBuilder msg = new StringBuilder();
        for(int i = startIndex; i < str.length; i++)
        {
            if(!msg.isEmpty())
                msg.append(' ');

            msg.append(str[i]);
        }

        return msg.toString();
    }

    private String _formatString(Player player, String str)
    {
        if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null)
        {
            return PlaceholderAPI.setPlaceholders(player, str);
        }
        else
        {
            log.warn("PlaceholdersAPI not found, please install it!");
            return str;
        }
    }

    private String formatString(Player player, String str)
    {
        return ChatColor.translateAlternateColorCodes('&', _formatString(player, str));
    }

}
