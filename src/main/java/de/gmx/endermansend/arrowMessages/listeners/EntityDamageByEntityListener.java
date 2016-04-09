package de.gmx.endermansend.arrowMessages.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
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
        String title = getTitle(arrow);

        String message = getMessage(arrow);

        Player target = (Player) e.getEntity();

        ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
        BookMeta bookContent = (BookMeta) book.getItemMeta();
        bookContent.setTitle(title);
        bookContent.addPage(message);
        bookContent.setAuthor("Unknown");
        book.setItemMeta(bookContent);

        target.getInventory().addItem(book, new ItemStack(Material.ARROW));

    }

    private String getTitle(Arrow arrow) {
        List<MetadataValue> titleData = arrow.getMetadata("Title");
        String title = "";

        for (MetadataValue metaValue : titleData)
            title += metaValue.asString();
        return title;
    }

    private String getMessage(Arrow arrow) {
        List<MetadataValue> messageData = arrow.getMetadata("Message");
        String message = "";

        for (MetadataValue metaValue : messageData)
            message += metaValue.asString();
        return message;
    }

}
