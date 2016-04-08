package de.gmx.endermansend.arrowMessages.items;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemHandler {

    /**
     * Creates and adds a new arrow item that can be crafted with an arrow and a written book.
     */
    public void setUpArrowMessageItem() {
        ItemStack arrow = new ItemStack(Material.ARROW);
        ItemMeta meta = arrow.getItemMeta();
        meta.setDisplayName("Arrow Message");
        arrow.setItemMeta(meta);

        ShapedRecipe recipe = new ShapedRecipe(arrow);
        recipe.shape("A ", " B");
        recipe.setIngredient('A', Material.ARROW);
        recipe.setIngredient('B', Material.WRITTEN_BOOK);
        Bukkit.addRecipe(recipe);
    }

}
