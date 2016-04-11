package de.gmx.endermansend.arrowMessages.listeners;

import de.gmx.endermansend.arrowMessages.main.ArrowMessages;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class CraftItemListener implements Listener {

    private ItemStack referenceArrow;
    private String pageEndTag;

    public CraftItemListener(ArrowMessages main) {
        this.referenceArrow = main.getItemHandler().getReferenceArrow();
        this.pageEndTag = main.getPageEndTag();
    }

    @EventHandler
    public void onCraftItem(CraftItemEvent e) {

        if(!isCorrectRecipe(e))
            return;

        CraftingInventory craftingInventory = e.getInventory();

        ItemStack[] ingredients = craftingInventory.getMatrix();
        for(ItemStack ingredient : ingredients) {
            if (ingredient.getItemMeta() instanceof BookMeta) {

                BookMeta bookContent = (BookMeta) ingredient.getItemMeta();
                ItemStack result = e.getRecipe().getResult();

                List<String> lore = new ArrayList<String>();
                for(String page : bookContent.getPages())
                    lore.add(page.replace("\n", "") + pageEndTag);

                ItemMeta arrowMeta = result.getItemMeta();
                arrowMeta.setLore(lore);
                arrowMeta.setDisplayName(bookContent.getTitle());
                result.setItemMeta(arrowMeta);
                craftingInventory.setResult(result);

                return;
            }
        }

    }

    private boolean isCorrectRecipe(CraftItemEvent e) {
        ItemStack result = e.getRecipe().getResult();
        return result.equals(referenceArrow) && e.getInventory() instanceof  CraftingInventory;
    }

}
