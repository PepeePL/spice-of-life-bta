package qapy.spiceoflife;

import net.fabricmc.api.ModInitializer;
import net.minecraft.core.item.Items;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import qapy.spiceoflife.util.SuperFoodsRegistry;
import turniplabs.halplibe.HalpLibe;
import turniplabs.halplibe.event.defs.CommonEvents;
import turniplabs.halplibe.util.dependency.Key;

public class SpiceOfLifeMod implements ModInitializer {
	public static final String MOD_ID = HalpLibe.registerMod("spiceoflife", true);
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		CommonEvents.RECIPES_READY.listen(Key.of(MOD_ID), this::recipesReady);
		LOGGER.info("Spice of Life: BTA initialized.");
	}

	private void recipesReady() {
		SuperFoodsRegistry.addSuperFood(Items.FOOD_APPLE_GOLD);
	}
}
