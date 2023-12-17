package me.oneqxz.itemrestrictplus.listeners;

import me.oneqxz.itemrestrictplus.settings.types.RestrictionType;
import me.oneqxz.itemrestrictplus.utils.RestrictUtils;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BreakBlockListener implements Listener {

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private void onBreak(BlockBreakEvent event)
    {
        Player p = event.getPlayer();
        Block block = event.getBlock();

        if(RestrictUtils.process(block.getType(), RestrictionType.BREAK, p))
            event.setCancelled(true);
    }

}
