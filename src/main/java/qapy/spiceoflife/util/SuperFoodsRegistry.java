package qapy.spiceoflife.util;

import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SuperFoodsRegistry {
	private static final List<Item> SUPERFOODS = new ArrayList<>();

	public static void addSuperFood(Item item) {
		SUPERFOODS.add(Objects.requireNonNull(item));
	}
	public static boolean isSuperfood(ItemStack stack) {
		Item item = stack.getItem();
		return SUPERFOODS.contains(item);
	}
}
