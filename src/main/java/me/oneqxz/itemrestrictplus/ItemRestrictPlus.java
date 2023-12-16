package me.oneqxz.itemrestrictplus;

import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;

public final class ItemRestrictPlus extends JavaPlugin {

    public static ItemRestrictPlus api;

    @Override
    public void onEnable() {
        api = this;
    }

    @Override
    public void onDisable() {

    }
}
