package de.gmx.endermansend.arrowMessages.main;

import de.gmx.endermansend.arrowMessages.items.ItemHandler;
import org.bukkit.plugin.java.JavaPlugin;

public class ArrowMessages extends JavaPlugin {

    ItemHandler itemHandler;

    @Override
    public void onEnable() {

        itemHandler = new ItemHandler();
        itemHandler.setUpArrowMessageItem();

        getLogger().info("Enabled");

    }

    @Override
    public void onDisable() {
        getLogger().info("Disabled");
    }

}
