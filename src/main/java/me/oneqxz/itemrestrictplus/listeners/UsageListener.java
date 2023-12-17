package me.oneqxz.itemrestrictplus.listeners;

import me.oneqxz.itemrestrictplus.settings.types.RestrictionType;
import me.oneqxz.itemrestrictplus.utils.RestrictUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class UsageListener implements Listener {

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private void onInteract(PlayerInteractEvent event)
    {
        Player p = event.getPlayer();
        ItemStack item = event.getItem();

        if(event.getClickedBlock() != null)
        {
            if(RestrictUtils.process(event.getMaterial(), RestrictionType.USAGE, p))
            {
                event.setCancelled(true);
                return;
            }
        }

        if(RestrictUtils.process(item, RestrictionType.USAGE, p))
        {
            event.setCancelled(true);
            return;
        }

        if(RestrictUtils.process(p.getInventory().getItemInMainHand(), RestrictionType.USAGE, p))
        {
            event.setCancelled(true);
            return;
        }

        if(RestrictUtils.process(p.getInventory().getItemInOffHand(), RestrictionType.USAGE, p))
            event.setCancelled(true);

    }

}
