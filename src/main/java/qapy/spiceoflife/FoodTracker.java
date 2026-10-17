package qapy.spiceoflife;

import com.mojang.nbt.tags.CompoundTag;
import com.mojang.nbt.tags.ListTag;
import net.minecraft.core.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayDeque;
import java.util.Deque;

public class FoodTracker {
	private final int MAX_FOODS = 10;
	private final Deque<ItemStack> foodHistory = new ArrayDeque<>();

	public FoodTracker() {}

	public void addToList(ItemStack stack) {
		ItemStack foodStack = stack.copy();
		foodStack.stackSize = 1;
		this.foodHistory.add(foodStack);
		if (this.foodHistory.size() > MAX_FOODS) {
			this.foodHistory.removeFirst();
		}
	}

	public int getFoodCount(ItemStack stack) {
		return Math.toIntExact(foodHistory.stream()
			.filter(s -> s.isItemEqual(stack))
			.count());
	}

	public double getHealModifier(ItemStack stack) {
		int count = this.getFoodCount(stack);
		if (count == 0) {
			return 1.0;
		}
		return Math.pow(0.52, count);
	}

	public ListTag save(@NotNull ListTag parentTag) {
		Object[] foodArray = this.foodHistory.toArray();
		for (int i = 0; i < this.foodHistory.size(); i++) {
			ItemStack stack = (ItemStack) foodArray[i];
			if (stack != null) {
				CompoundTag itemTag = new CompoundTag();
				stack.writeToNBT(itemTag);
				parentTag.addTag(itemTag);
			}
		}
		return parentTag;
	}
	public void load(@NotNull ListTag parentTag) {
		this.foodHistory.clear();
		for (int i = 0; i < parentTag.tagCount(); i++) {
			CompoundTag itemTag = (CompoundTag)parentTag.tagAt(i);
			ItemStack stack = ItemStack.readItemStackFromNbt(itemTag);
			foodHistory.add(stack);
		}
	}
}
