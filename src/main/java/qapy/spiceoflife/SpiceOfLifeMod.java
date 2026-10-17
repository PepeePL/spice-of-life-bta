package qapy.spiceoflife;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import turniplabs.halplibe.HalpLibe;
import turniplabs.halplibe.event.defs.CommonEvents;
import turniplabs.halplibe.util.dependency.Key;

public class SpiceOfLifeMod implements ModInitializer {
	public static final String MOD_ID = HalpLibe.registerMod("spice-of-life-bta", true);
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
//		CommonEvents.BEFORE_GAME_START.listen(Key.of(MOD_ID), this::beforeGameStart);
//		CommonEvents.AFTER_GAME_START.listen(Key.of(MOD_ID), this::afterGameStart);
		LOGGER.info("Spice of Life: BTA initialized.");
	}

//	public void beforeGameStart() {
//		LOGGER.info("Before game start.");
//	}
//
//	public void afterGameStart() {
//		LOGGER.info("After game start.");
//	}
}
