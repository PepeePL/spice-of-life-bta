package qapy.spiceoflife.mixin;

import net.minecraft.core.entity.Mob;
import net.minecraft.core.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import qapy.spiceoflife.FoodTrackerAccess;

@Mixin(Mob.class)
public class MobMixin {
	@Inject(method = "eatFood", at = @org.spongepowered.asm.mixin.injection.At("TAIL"))
	public void spiceOfLife$addToList(ItemStack stack, CallbackInfo ci) {
		Mob mob = (Mob) (Object) this;
		if (mob instanceof FoodTrackerAccess foodTrackerAccess) {
			foodTrackerAccess.spice_of_life_bta$getFoodTracker().addToList(stack);
		}
	}
}
