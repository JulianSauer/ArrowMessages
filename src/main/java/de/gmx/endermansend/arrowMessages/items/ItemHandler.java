package de.gmx.endermansend.arrowMessages.items;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemHandler {

    private ItemStack referenceArrow;

    private ItemMeta referenceArrowMeta;

    public ItemHandler() {
        referenceArrow = new ItemStack(Material.ARROW);
        referenceArrowMeta = referenceArrow.getItemMeta();
        referenceArrowMeta.setDisplayName("Arrow Message");
        referenceArrow.setItemMeta(referenceArrowMeta);
    }

    /**
     * Creates and adds a new arrow item that can be crafted with an arrow and a written book.
     */
    public void setUpArrowMessageItem() {

        ShapelessRecipe recipe = new ShapelessRecipe(referenceArrow);
        recipe.addIngredient(Material.ARROW);
        recipe.addIngredient(Material.WRITTEN_BOOK);
        Bukkit.addRecipe(recipe);
    }

    public ItemStack getReferenceArrow() {
        return referenceArrow;
    }

    public ItemMeta getReferenceArrowMeta() {
        return referenceArrowMeta;
    }

}
