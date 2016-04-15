package de.gmx.endermansend.arrowMessages.config;

/**
 * Defines paths to get values from config.yml
 */
public class GetValuesFromConfig {

    ConfigHandler config;

    GetValuesFromConfig(ConfigHandler config) {
        this.config = config;
    }

    public boolean spectralGlowing() {
        return config.getBooleanFromConfig("spectralGlowing");
    }

}
