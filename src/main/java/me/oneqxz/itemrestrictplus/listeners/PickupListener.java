package me.oneqxz.itemrestrictplus.listeners;

import io.papermc.paper.event.player.PlayerPickItemEvent;
import me.oneqxz.itemrestrictplus.settings.types.RestrictionType;
import me.oneqxz.itemrestrictplus.utils.RestrictUtils;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class PickupListener implements Listener {

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private void onItemPickup(PlayerPickupItemEvent event)
    {
        Player p = event.getPlayer();
        ItemStack item = event.getItem().getItemStack();

        if(RestrictUtils.process(item, RestrictionType.PICKUP, p))
        {
            event.setCancelled(true);
            Location loc = event.getItem().getLocation();
            event.getItem().teleport(new Location(loc.getWorld(), loc.getX() + getRandomInt(), loc.getY() + getRandomInt(), loc.getZ() + getRandomInt()));
        }
    }

    private int getRandomInt() {
        return new Random().nextInt(5);
    }

}
