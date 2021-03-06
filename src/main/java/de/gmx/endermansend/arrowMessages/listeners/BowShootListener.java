package de.gmx.endermansend.arrowMessages.listeners;

import de.gmx.endermansend.arrowMessages.items.ItemHandler;
import de.gmx.endermansend.arrowMessages.main.ArrowMessages;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;

import java.util.List;

public class BowShootListener implements Listener {

    private ItemHandler itemHandler;

    public BowShootListener() {
        this.itemHandler = ArrowMessages.getInstance().getItemHandler();
    }

    @EventHandler
    public void onBowShoot(EntityShootBowEvent e) {

        LivingEntity livingEntity = e.getEntity();
        if (livingEntity instanceof Player) {
            Player player = (Player) livingEntity;

            ItemStack arrow = getShotArrow(player);
            if (arrow == null)
                return;
            ItemMeta arrowMeta = arrow.getItemMeta();

            if (isArrowMessage(arrowMeta)) {
                e.getProjectile().setMetadata("Title", new FixedMetadataValue(ArrowMessages.getInstance(), arrowMeta.getDisplayName()));
                e.getProjectile().setMetadata("Message", new FixedMetadataValue(ArrowMessages.getInstance(), arrowMeta.getLore()));
            }

        }

    }

    private ItemStack getShotArrow(Player player) {

        PlayerInventory inventory = player.getInventory();
        ItemStack arrow = inventory.getItemInOffHand();
        if (isArrow(arrow))
            return arrow;

        for (int i = 0; i < inventory.getSize(); i++) {
            arrow = inventory.getItem(i);
            if (isArrow(arrow))
                return arrow;
        }

        return null;
    }

    private boolean isArrow(ItemStack arrow) {
        if (arrow == null)
            return false;
        return arrow.getType().equals(Material.ARROW)
                || arrow.getType().equals(Material.SPECTRAL_ARROW)
                || arrow.getType().equals(Material.TIPPED_ARROW);
    }

    private boolean isArrowMessage(ItemMeta arrowMeta) {
        List<String> lore = arrowMeta.getLore();
        if (lore == null)
            return false;
        if (lore.isEmpty())
            return false;
        for (String s : lore) {
            if (!s.equals(""))
                return true;
        }
        return false;
    }

}
