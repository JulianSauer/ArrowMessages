package de.gmx.endermansend.arrowMessages.listeners;

import de.gmx.endermansend.arrowMessages.main.ArrowMessages;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.metadata.MetadataValue;

import java.util.ArrayList;
import java.util.List;

public abstract class ArrowShotListener implements Listener {

    protected String pageEndTag;
    protected String lineEndTag;

    public ArrowShotListener() {
        this.pageEndTag = ArrowMessages.getInstance().getPageEndTag();
        this.lineEndTag = ArrowMessages.getInstance().getLineEndTag();
    }

    protected boolean giveItemsToPlayer(Player player, Arrow arrow) {
        String title = getTitle(arrow);
        List<String> message = getMessage(arrow);

        if (message == null)
            return false;

        ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
        BookMeta bookContent = (BookMeta) book.getItemMeta();
        bookContent.setTitle(title);
        bookContent.setPages(message);
        bookContent.setAuthor("Unknown");
        book.setItemMeta(bookContent);

        player.getInventory().addItem(book);
        return true;
    }

    protected String getTitle(Arrow arrow) {
        List<MetadataValue> titleData = arrow.getMetadata("Title");
        String title = "";

        for (MetadataValue metaValue : titleData)
            title += metaValue.asString();
        return title;
    }

    protected List<String> getMessage(Arrow arrow) {
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
                message.add(page.substring(2).replace(lineEndTag, "\n"));
            else if (!page.equals("]")) // Removes "]"
                message.add(page.replace(lineEndTag, "\n"));
        }

        message.set(0, message.get(0).substring(1)); // Removes "["

        return message;
    }

}
