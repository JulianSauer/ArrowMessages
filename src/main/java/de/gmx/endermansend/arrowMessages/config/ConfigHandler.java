package de.gmx.endermansend.arrowMessages.config;

import de.gmx.endermansend.arrowMessages.main.ArrowMessages;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;
import java.util.logging.Logger;

/**
 * Handles everything config specific like loading the config.yml file safely and getting values from it.
 * Concrete value paths have to be defined in a class implementing this interface (well or extending because it's not
 * actually an interface but shh!).
 */
public class ConfigHandler {

    public GetValuesFromConfig get;

    private ArrowMessages main;
    private Logger logger;
    private FileConfiguration config;

    public ConfigHandler(ArrowMessages main) {

        this.main = main;
        this.logger = this.main.getLogger();

        if (!loadConfig())
            createDefaultConfig();

        get = new GetValuesFromConfig(this);

    }

    /**
     * Saves the config after values have been change. This method is not used and hasn't been tested yet.
     */
    public void saveConfig() {
        logger.info("Saving config");
        main.saveConfig();
        logger.info("config saved");
    }

    /**
     * Tries to convert the value found under the given path to a boolean. If it cannot be found in config.yml, an
     * error message will be printed and a default one will be used.
     *
     * @param path Path to the variable
     * @return Value found in config.yml (default value if none is found)
     */
    protected boolean getBooleanFromConfig(String path) {
        if (!config.isSet(path) || !config.isBoolean(path))
            noValueFound(path);
        return config.getBoolean(path);
    }

    /**
     * Saves the default configuration file and stores it's content to config.
     */
    private void createDefaultConfig() {

        logger.info("Creating default config");
        main.saveDefaultConfig();

        config = main.getConfig();
        logger.info("Config loaded");

    }

    /**
     * Tries to load the config.yml from disk. This method returns false if no matching file was found. In this case a
     * default config should be loaded.
     *
     * @return True if the config could be loaded
     */
    private boolean loadConfig() {

        logger.info("Loading config");

        if (this.configExists()) {
            config = main.getConfig();
            logger.info("Config loaded");
            return true;
        }

        return false;

    }

    /**
     * Prints a warning to the log if a value was not found under the specified path.
     *
     * @param path Path to the missing value
     */
    private void noValueFound(String path) {
        logger.warning("Value is missing or of wrong type: " + path);
        logger.warning("Using default value");
        logger.warning("Delete config.yml to get a default one");
    }

    /**
     * Checks if a config.yml exists in the plugin folder.
     *
     * @return True if a config.yml was found
     */
    private boolean configExists() {

        File[] files = main.getDataFolder().listFiles();
        if (files == null)
            return false;

        for (File file : files) {
            if (file.getName().equals("config.yml")) {
                return true;
            }
        }

        return false;

    }

}
