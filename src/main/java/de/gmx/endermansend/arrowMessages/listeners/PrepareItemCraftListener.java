package de.gmx.endermansend.arrowMessages.listeners;

import de.gmx.endermansend.arrowMessages.main.ArrowMessages;
import org.bukkit.ChatColor;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class PrepareItemCraftListener implements Listener {


    private ItemStack referenceArrow;

    private ChatColor titleColor;
    private ChatColor loreColor;
    private String pageEndTag;
    private String lineEndTag;

    public PrepareItemCraftListener() {
        ArrowMessages main = ArrowMessages.getInstance();
        this.referenceArrow = main.getItemHandler().getReferenceArrow();
        this.titleColor = main.getTitleColor();
        this.loreColor = main.getLoreColor();
        this.pageEndTag = main.getPageEndTag();
        this.lineEndTag = main.getLineEndTag();
    }

    @EventHandler
    public void onPrepareItemCraft(PrepareItemCraftEvent e) {

        if (!(e.getInventory() instanceof CraftingInventory))
            return;

        if (!isCorrectRecipe(e))
            return;

        CraftingInventory craftingInventory = e.getInventory();

        ItemStack[] ingredients = craftingInventory.getMatrix();
        for (ItemStack ingredient : ingredients) {
            if (ingredient.getItemMeta() instanceof BookMeta) {

                BookMeta bookContent = (BookMeta) ingredient.getItemMeta();
                ItemStack result = e.getRecipe().getResult();

                List<String> lore = new ArrayList<String>();
                for (String page : bookContent.getPages())
                    lore.add(loreColor + ChatColor.stripColor(page).replace("\n", lineEndTag) + pageEndTag);

                ItemMeta arrowMeta = result.getItemMeta();

                arrowMeta.addEnchant(Enchantment.ARROW_INFINITE, 0, true);
                arrowMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                arrowMeta.setLore(lore);
                arrowMeta.setDisplayName(titleColor + ChatColor.stripColor(bookContent.getTitle()));

                result.setItemMeta(arrowMeta);
                craftingInventory.setResult(result);

                return;
            }
        }

    }

    private boolean isCorrectRecipe(PrepareItemCraftEvent e) {
        ItemStack result = e.getRecipe().getResult();
        return result.equals(referenceArrow) && e.getInventory() instanceof CraftingInventory;
    }

}
