package de.gmx.endermansend.arrowMessages.config;


import org.bukkit.ChatColor;

/**
 * Defines paths to get values from config.yml
 */
public class GetValuesFromConfig {

    ConfigHandler config;

    GetValuesFromConfig(ConfigHandler config) {
        this.config = config;
    }

    public ChatColor titleColor() {
        String color = config.getStringFromConfig("titleColor");
        try {
            return ChatColor.valueOf(color);
        } catch (IllegalArgumentException e) {
            config.logger.warning(color + " is not a valid ChatColor");
        }
        return ChatColor.WHITE;
    }

    public ChatColor loreColor() {
        String color = config.getStringFromConfig("loreColor");
        try {
            return ChatColor.valueOf(color);
        } catch (IllegalArgumentException e) {
            config.logger.warning(color + " is not a valid ChatColor");
        }
        return ChatColor.WHITE;
    }

    public boolean spectralGlowing() {
        return config.getBooleanFromConfig("spectralGlowing");
    }

}
