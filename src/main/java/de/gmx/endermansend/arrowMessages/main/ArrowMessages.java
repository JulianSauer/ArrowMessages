package de.gmx.endermansend.arrowMessages.main;

import de.gmx.endermansend.arrowMessages.config.ConfigHandler;
import de.gmx.endermansend.arrowMessages.items.ItemHandler;
import de.gmx.endermansend.arrowMessages.listeners.*;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class ArrowMessages extends JavaPlugin {

    private static ArrowMessages instance;

    private ItemHandler itemHandler;
    private ConfigHandler config;

    private ChatColor titleColor;
    private ChatColor loreColor;
    private String pageEndTag;
    private String lineEndTag;

    @Override
    public void onEnable() {

        instance = this;

        itemHandler = new ItemHandler();
        itemHandler.setUpArrowMessageItem();

        config = new ConfigHandler();

        titleColor = config.get.titleColor();
        loreColor = config.get.loreColor();
        pageEndTag = ChatColor.GOLD + "" + loreColor;
        lineEndTag = ChatColor.DARK_PURPLE + " " + loreColor;

        PluginManager manager = getServer().getPluginManager();
        manager.registerEvents(new PrepareItemCraftListener(), this);
        manager.registerEvents(new BowShootListener(), this);
        manager.registerEvents(new EntityDamageByEntityListener(), this);
        manager.registerEvents(new PlayerPickupArrowListener(), this);
        manager.registerEvents(new ProjectileHitListener(), this);

        getLogger().info("Enabled");

    }

    @Override
    public void onDisable() {
        getLogger().info("Disabled");
    }

    public static ArrowMessages getInstance() {
        return instance;
    }

    public ItemHandler getItemHandler() {
        return itemHandler;
    }

    public ChatColor getTitleColor() {
        return titleColor;
    }

    public ChatColor getLoreColor() {
        return loreColor;
    }

    public String getPageEndTag() {
        return pageEndTag;
    }

    public String getLineEndTag() {
        return lineEndTag;
    }

    public ConfigHandler getConfigHandler() {
        return config;
    }

}
