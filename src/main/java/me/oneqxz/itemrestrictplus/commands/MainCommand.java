package me.oneqxz.itemrestrictplus.commands;

import me.oneqxz.itemrestrictplus.settings.Config;
import me.oneqxz.itemrestrictplus.settings.Restricted;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class MainCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(args.length > 0)
        {
            if(args[0].equalsIgnoreCase("reload"))
            {
                try
                {
                    Config.getInstance().reload();
                    Restricted.getInstance().reload();

                    commandSender.sendMessage(getString(Config.getInstance().getCommandSettings().getReload(), command.getLabel()));
                }
                catch (Throwable t)
                {
                    t.printStackTrace();
                    commandSender.sendMessage("\247cAn error occurred during processing, please check the console output to fix it");
                }
            }
            else if(args[0].equalsIgnoreCase("help"))
            {
                commandSender.sendMessage(getString(Config.getInstance().getCommandSettings().getHelp(), command.getLabel()));
            }
            else
            {
                commandSender.sendMessage(getString(Config.getInstance().getCommandSettings().getUnknownCommand(), command.getLabel()));
            }
        }
        else
        {
            commandSender.sendMessage(getString(Config.getInstance().getCommandSettings().getHelp(), command.getLabel()));
        }

        return true;
    }

    private String getString(String msg, String cmdName)
    {
        return ChatColor.translateAlternateColorCodes('&', msg).replace("<label>", cmdName);
    }
}
