package de.gmx.endermansend.arrowMessages.main;

import org.bukkit.plugin.java.JavaPlugin;

public class ArrowMessages extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("Enabled");
    }

    @Override
    public void onDisable() {
        getLogger().info("Disabled");
    }

}
