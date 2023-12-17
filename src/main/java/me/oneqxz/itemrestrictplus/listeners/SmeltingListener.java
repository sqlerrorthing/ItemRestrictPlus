package me.oneqxz.itemrestrictplus.listeners;

import me.oneqxz.itemrestrictplus.settings.types.RestrictionType;
import me.oneqxz.itemrestrictplus.utils.RestrictUtils;
import org.bukkit.Material;
import org.bukkit.block.Furnace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.FurnaceSmeltEvent;
import org.bukkit.inventory.ItemStack;

public class SmeltingListener implements Listener {

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private void onItemCrafted(FurnaceSmeltEvent event) {
        ItemStack item = event.getSource();
        Furnace f = (Furnace) event.getBlock().getState();

        if(!f.getInventory().getViewers().isEmpty())
        {
            Player p = (Player) f.getInventory().getViewers().get(0);

            if(RestrictUtils.process(item, RestrictionType.SMELTING, p))
            {
                event.setCancelled(true);
                event.setResult(new ItemStack(Material.AIR));
            }
        }
    }

}
