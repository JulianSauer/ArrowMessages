package de.gmx.endermansend.arrowMessages.main;

import de.gmx.endermansend.arrowMessages.items.ItemHandler;
import de.gmx.endermansend.arrowMessages.listeners.BowShootListener;
import de.gmx.endermansend.arrowMessages.listeners.EntityDamageByEntityListener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class ArrowMessages extends JavaPlugin {

    ItemHandler itemHandler;

    @Override
    public void onEnable() {

        itemHandler = new ItemHandler();
        itemHandler.setUpArrowMessageItem();

        PluginManager manager = getServer().getPluginManager();
        manager.registerEvents(new BowShootListener(this), this);
        manager.registerEvents(new EntityDamageByEntityListener(), this);

        getLogger().info("Enabled");

    }

    @Override
    public void onDisable() {
        getLogger().info("Disabled");
    }

    public ItemHandler getItemHandler() {
        return itemHandler;
    }

}
