package me.byteful.mod.hopliteautopet;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HopliteAutoPetMod implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("hopliteautopet");

    @Override
    public void onInitialize() {
        LOGGER.info("Starting Hoplite AutoPet Mod...");
        // Not much to this mod! Very lightweight.
    }
}
