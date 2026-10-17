package qapy.spiceoflife.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.core.entity.ConsumedFood;
import net.minecraft.core.entity.Mob;
import net.minecraft.core.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import qapy.spiceoflife.FoodTrackerAccess;

@Mixin(ConsumedFood.class)
public abstract class ConsumedFoodMixin {
	@Shadow
	private Mob entity;

	@ModifyExpressionValue(
		method = "<init>",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/core/item/ItemFood;getHealAmount(Lnet/minecraft/core/item/ItemStack;)I"
		)
	)
	private int spiceOfLife$modifyInitialHeal(int amount, @Local(argsOnly = true) ItemStack stack) {
		if (entity instanceof FoodTrackerAccess access) {
			double modifier = access.spice_of_life_bta$getFoodTracker()
				.getHealModifier(stack);

			return (int) Math.ceil(amount * modifier - 0.5);
		}

		return amount;
	}

	@ModifyExpressionValue(
		method = "addFood",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/core/item/ItemFood;getHealAmount(Lnet/minecraft/core/item/ItemStack;)I"
		)
	)
	public int spiceOfLife$addFood(int amount, @Local(argsOnly = true) ItemStack additionalStack) {
		if (this.entity instanceof FoodTrackerAccess access) {
			double modifier = access.spice_of_life_bta$getFoodTracker()
				.getHealModifier(additionalStack);

			return (int) Math.ceil(amount * modifier - 0.5);
		}

		return amount;
	}
}
