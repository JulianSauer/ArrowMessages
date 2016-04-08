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
import org.bukkit.metadata.MetadataValue;

public class BowShootListener implements Listener {

    private ArrowMessages main;
    private ItemHandler itemHandler;

    private ItemMeta referenceArrowMeta;

    public BowShootListener(ArrowMessages main) {
        this.main = main;
        this.itemHandler = main.getItemHandler();
        this.referenceArrowMeta = itemHandler.getReferenceArrowMeta();
    }

    @EventHandler
    public void onBowShoot(EntityShootBowEvent e) {

        LivingEntity livingEntity = e.getEntity();
        if (livingEntity instanceof Player) {
            Player player = (Player) livingEntity;

            ItemStack shotArrow = getShotArrow(player);

            if(shotArrow.getItemMeta().equals(referenceArrowMeta)) {
                e.getProjectile().setMetadata("Arrow Message", new FixedMetadataValue(main, "A very important message should be written here.."));
            }

        }

    }

    private ItemStack getShotArrow(Player player) {

        Inventory inventory = player.getInventory();
        ItemStack arrow;

        for (int i = 0; i < inventory.getSize(); i++) {
            arrow = inventory.getItem(i);
            if(arrow.getType().equals(Material.ARROW)) {
                return arrow;
            }
        }

        return null;
    }

}
