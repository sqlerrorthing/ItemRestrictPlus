package me.oneqxz.itemrestrictplus.listeners;

import me.oneqxz.itemrestrictplus.settings.types.RestrictionType;
import me.oneqxz.itemrestrictplus.utils.RestrictUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCreativeEvent;
import org.bukkit.inventory.ItemStack;

public class CreativeListener implements Listener {

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private void onCreativeEvent(InventoryCreativeEvent event)
    {
        ItemStack cursorItem = event.getCursor();
        Player p = (Player) event.getWhoClicked();

        if(RestrictUtils.process(cursorItem, RestrictionType.CREATIVE, p))
        {
            event.setCancelled(true);
            event.setCursor(new ItemStack(Material.AIR));
        }
    }

}
