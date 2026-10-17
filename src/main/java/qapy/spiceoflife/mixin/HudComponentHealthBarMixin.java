package qapy.spiceoflife.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.hud.component.HudComponentHealthBar;
import net.minecraft.core.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import qapy.spiceoflife.FoodTrackerAccess;

@Mixin(HudComponentHealthBar.class)
public class HudComponentHealthBarMixin {
	@ModifyExpressionValue(
		method = "getPotentialHealing",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/core/item/ItemFood;getHealAmount(Lnet/minecraft/core/item/ItemStack;)I"
		)
	)
	private int spiceOfLife$modifyPotentialHealing(int amount, @Local ItemStack held) {
		if (Minecraft.getMinecraft().thePlayer instanceof FoodTrackerAccess access) {
			double modifier = access.spice_of_life_bta$getFoodTracker()
				.getHealModifier(held);

			return (int) Math.ceil(amount * modifier - 0.5);
		}
		return amount;
	}
}
