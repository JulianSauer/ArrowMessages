package de.gmx.endermansend.arrowMessages.listeners;

import de.gmx.endermansend.arrowMessages.items.ItemHandler;
import de.gmx.endermansend.arrowMessages.main.ArrowMessages;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;

import java.util.List;

public class BowShootListener implements Listener {

    private ArrowMessages main;
    private ItemHandler itemHandler;

    public BowShootListener(ArrowMessages main) {
        this.main = main;
        this.itemHandler = main.getItemHandler();
    }

    @EventHandler
    public void onBowShoot(EntityShootBowEvent e) {

        LivingEntity livingEntity = e.getEntity();
        if (livingEntity instanceof Player) {
            Player player = (Player) livingEntity;

            ItemMeta arrowMeta = getShotArrow(player).getItemMeta();

            if (isArrowMessage(arrowMeta)) {
                e.getProjectile().setMetadata("Title", new FixedMetadataValue(main, arrowMeta.getDisplayName()));
                e.getProjectile().setMetadata("Message", new FixedMetadataValue(main, arrowMeta.getLore()));
            }

        }

    }

    private ItemStack getShotArrow(Player player) {

        Inventory inventory = player.getInventory();
        ItemStack arrow;

        for (int i = 0; i < inventory.getSize(); i++) {
            arrow = inventory.getItem(i);
            if (arrow == null)
                continue;
            else if (arrow.getType().equals(Material.ARROW)) {
                return arrow;
            }
        }

        return null;
    }

    private boolean isArrowMessage(ItemMeta arrowMeta) {
        List<String> lore = arrowMeta.getLore();
        if(lore == null)
            return false;
        if(lore.isEmpty())
            return false;
        for(String s : lore) {
            if(!s.equals(""))
                return true;
        }
        return false;
    }

}
