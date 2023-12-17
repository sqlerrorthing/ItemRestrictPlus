package me.oneqxz.itemrestrictplus;

import me.oneqxz.itemrestrictplus.commands.MainCommand;
import me.oneqxz.itemrestrictplus.listeners.*;
import me.oneqxz.itemrestrictplus.settings.Config;
import me.oneqxz.itemrestrictplus.settings.Restricted;
import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;

public final class ItemRestrictPlus extends JavaPlugin {

    public static ItemRestrictPlus api;

    @Override
    public void onEnable() {
        api = this;

        Config.getInstance();
        Restricted.getInstance();

        getServer().getPluginManager().registerEvents(new CraftingListener(), this);
        getServer().getPluginManager().registerEvents(new BreakBlockListener(), this);
        getServer().getPluginManager().registerEvents(new CreativeListener(), this);
        getServer().getPluginManager().registerEvents(new DropListener(), this);
        getServer().getPluginManager().registerEvents(new PickupListener(), this);
        getServer().getPluginManager().registerEvents(new PlacementListener(), this);
        getServer().getPluginManager().registerEvents(new SmeltingListener(), this);
        getServer().getPluginManager().registerEvents(new UsageListener(), this);

        getCommand("itemrestrictplus").setExecutor(new MainCommand());
    }

    @Override
    public void onDisable() {

    }
}
