package de.gmx.endermansend.arrowMessages.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.MetadataValue;

import java.util.ArrayList;
import java.util.List;

public class EntityDamageByEntityListener implements Listener {

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent e) {

        Entity entity = e.getDamager();

        if (!(entity instanceof Arrow) || !(e.getEntity() instanceof Player))
            return;

        Arrow arrow = (Arrow) entity;
        List<MetadataValue> metaValues = arrow.getMetadata("Arrow Message");
        List<String> message = new ArrayList<String>();
        for (MetadataValue metaValue : metaValues) {
            message.add(metaValue.asString());
        }

        Player target = (Player) e.getEntity();

        ItemStack messageBook = new ItemStack(Material.WRITTEN_BOOK);
        messageBook.getItemMeta().setLore(message);
        target.getInventory().addItem(messageBook, new ItemStack(Material.ARROW));

    }

}
