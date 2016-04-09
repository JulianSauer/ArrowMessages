package de.gmx.endermansend.arrowMessages.items;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;

public class ItemHandler {

    private ItemStack referenceArrow;

    public ItemHandler() {
        referenceArrow = new ItemStack(Material.ARROW);
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

}
