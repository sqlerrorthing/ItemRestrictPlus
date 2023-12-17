package me.oneqxz.itemrestrictplus.listeners;

import me.oneqxz.itemrestrictplus.settings.types.RestrictionType;
import me.oneqxz.itemrestrictplus.utils.RestrictUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.ItemStack;

public class CraftingListener implements Listener {

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private void onItemCrafted(PrepareItemCraftEvent event)
    {
        if(event.getRecipe() != null)
        {
            ItemStack item = event.getRecipe().getResult();
            if(!event.getViewers().isEmpty())
            {
                Player p = (Player) event.getViewers().get(0);

                if(RestrictUtils.process(item, RestrictionType.CRAFTING, p))
                {
                    event.getInventory().setResult(null);
                }
            }
        }
    }

}
