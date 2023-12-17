package me.oneqxz.itemrestrictplus.listeners;

import me.oneqxz.itemrestrictplus.settings.types.RestrictionType;
import me.oneqxz.itemrestrictplus.utils.RestrictUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

public class PlacementListener implements Listener {

    @EventHandler(priority = EventPriority.LOWEST)
    private void onInteract(BlockPlaceEvent event) {
        Player p = event.getPlayer();

        if(RestrictUtils.process(p.getInventory().getItemInMainHand(), RestrictionType.PLACEMENT, p))
        {
            event.setCancelled(true);
            return;
        }

        if(RestrictUtils.process(p.getInventory().getItemInOffHand(), RestrictionType.PLACEMENT, p))
            event.setCancelled(true);
    }

}
