package io.github.dashtiss.infernocraft;

import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Infernocraft implements ModInitializer {

    private static final Logger LOGGER = LogManager.getLogger();

    public static final String MOD_ID = "infernocraft";
    public static final String MOD_NAME = "Infernocraft";
    public static final String MOD_VERSION = "0.1.0";


    public float totalTickDelta = 0;

    @Override
    public void onInitialize() {
        LOGGER.info("Infernocraft initializing");
        // Initialize and register blocks/items
        RegisterBlocks.initialize();

        RegisterItems.initialize();;

        LOGGER.info("Infernocraft initialization complete");
    }
}
