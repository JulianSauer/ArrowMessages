package de.gmx.endermansend.arrowMessages.listeners;

import de.gmx.endermansend.arrowMessages.main.ArrowMessages;
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

    private String pageEndTag;

    public EntityDamageByEntityListener(ArrowMessages main) {
        this.pageEndTag = main.getPageEndTag();
    }

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent e) {

        Entity entity = e.getDamager();

        if (!(entity instanceof Arrow) || !(e.getEntity() instanceof Player))
            return;

        Arrow arrow = (Arrow) entity;
        String title = getTitle(arrow);
        List<String> message = getMessage(arrow);

        if (message == null)
            return;

        Player target = (Player) e.getEntity();

        ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
        BookMeta bookContent = (BookMeta) book.getItemMeta();
        bookContent.setTitle(title);
        bookContent.setPages(message);
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

    private List<String> getMessage(Arrow arrow) {
        List<MetadataValue> messageData = arrow.getMetadata("Message");
        if (messageData == null)
            return null;
        else if (messageData.isEmpty())
            return null;

        String unformattedMessage = "";
        for (MetadataValue metaValue : messageData) {
            unformattedMessage += metaValue.asString();
        }

        List<String> message = new ArrayList<String>();
        for (String page : unformattedMessage.split(pageEndTag)) {
            if (page.startsWith(", ") && page.length() > 2)
                message.add(page.substring(2));
            else if (!page.equals("]")) // Removes "]"
                message.add(page);
        }

        message.set(0, message.get(0).substring(1)); // Removes "["

        return message;
    }

}
