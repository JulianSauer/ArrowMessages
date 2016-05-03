package de.gmx.endermansend.arrowMessages.listeners;

import de.gmx.endermansend.arrowMessages.main.ArrowMessages;
import org.bukkit.ChatColor;
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
    private String authorPseudonym;

    public ArrowShotListener() {
        ArrowMessages main = ArrowMessages.getInstance();
        this.pageEndTag = main.getPageEndTag();
        this.lineEndTag = main.getLineEndTag();
        this.authorPseudonym = main.getConfigHandler().get.authorPseudonym();
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
        bookContent.setAuthor(authorPseudonym);
        book.setItemMeta(bookContent);

        player.getInventory().addItem(book);
        return true;
    }

    protected String getTitle(Arrow arrow) {
        List<MetadataValue> titleData = arrow.getMetadata("Title");
        String title = "";

        for (MetadataValue metaValue : titleData)
            title += metaValue.asString();
        return ChatColor.WHITE + ChatColor.stripColor(title);
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
                message.add(ChatColor.BLACK + ChatColor.stripColor(page.substring(2).replace(lineEndTag, "\n")));
            else if (!page.equals("]")) // Removes "]"
                message.add(ChatColor.BLACK + ChatColor.stripColor(page.replace(lineEndTag, "\n")));
        }

        message.set(0, message.get(0).substring(3)); // Removes "°0["

        return message;
    }

}
