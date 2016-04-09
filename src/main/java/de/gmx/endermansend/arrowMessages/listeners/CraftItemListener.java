package de.gmx.endermansend.arrowMessages.listeners;

import de.gmx.endermansend.arrowMessages.main.ArrowMessages;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;

public class CraftItemListener implements Listener {

    private ItemStack referenceArrow;

    public CraftItemListener(ArrowMessages main) {
        this.referenceArrow = main.getItemHandler().getReferenceArrow();
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

                ItemMeta arrowMeta = result.getItemMeta();
                arrowMeta.setLore(bookContent.getPages());
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
