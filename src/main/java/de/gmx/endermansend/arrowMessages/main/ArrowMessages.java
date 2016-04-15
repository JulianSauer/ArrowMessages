package de.gmx.endermansend.arrowMessages.main;

import de.gmx.endermansend.arrowMessages.config.ConfigHandler;
import de.gmx.endermansend.arrowMessages.items.ItemHandler;
import de.gmx.endermansend.arrowMessages.listeners.*;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class ArrowMessages extends JavaPlugin {

    private ItemHandler itemHandler;
    private ConfigHandler config;

    private String pageEndTag;

    @Override
    public void onEnable() {

        itemHandler = new ItemHandler();
        itemHandler.setUpArrowMessageItem();

        config = new ConfigHandler(this);

        pageEndTag = ChatColor.GOLD + "";

        PluginManager manager = getServer().getPluginManager();
        manager.registerEvents(new CraftItemListener(this), this);
        manager.registerEvents(new BowShootListener(this), this);
        manager.registerEvents(new EntityDamageByEntityListener(this), this);
        manager.registerEvents(new PlayerPickupArrowListener(this), this);
        manager.registerEvents(new ProjectileHitListener(this), this);

        getLogger().info("Enabled");

    }

    @Override
    public void onDisable() {
        getLogger().info("Disabled");
    }

    public ItemHandler getItemHandler() {
        return itemHandler;
    }

    public String getPageEndTag() {
        return pageEndTag;
    }

    public ConfigHandler getConfigHandler() {
        return config;
    }

}
