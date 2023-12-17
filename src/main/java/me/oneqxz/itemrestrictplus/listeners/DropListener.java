package me.oneqxz.itemrestrictplus.listeners;

import me.oneqxz.itemrestrictplus.settings.types.RestrictionType;
import me.oneqxz.itemrestrictplus.utils.RestrictUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;

public class DropListener implements Listener {

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private void onItemDrop(PlayerDropItemEvent event) {
        Player p = event.getPlayer();
        ItemStack item = event.getItemDrop().getItemStack();

        if(RestrictUtils.process(item, RestrictionType.DROP, p))
        {
            event.setCancelled(true);
        }
    }

}
