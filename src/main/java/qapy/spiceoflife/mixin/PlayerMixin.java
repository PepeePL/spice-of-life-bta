package qapy.spiceoflife.mixin;

import com.mojang.nbt.tags.CompoundTag;
import com.mojang.nbt.tags.ListTag;
import net.minecraft.core.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import qapy.spiceoflife.FoodTracker;
import qapy.spiceoflife.FoodTrackerAccess;

@Mixin(Player.class)
public class PlayerMixin implements FoodTrackerAccess {
	@Unique
	private final FoodTracker pepeethings$foodTracker = new FoodTracker();

	@Override
	public FoodTracker spice_of_life_bta$getFoodTracker() {
		return pepeethings$foodTracker;
	}
	@Inject(method = "addAdditionalSaveData", at = @At("TAIL"))
	public void spiceOfLife$addAdditionalSaveData(CompoundTag tag, CallbackInfo ci) {
		tag.put("FoodHistory", pepeethings$foodTracker.save(new ListTag()));
	}
	@Inject(method = "readAdditionalSaveData", at = @At("TAIL"))
	public void spiceOfLife$readAdditionalSaveData(CompoundTag tag, CallbackInfo ci) {
		this.pepeethings$foodTracker.load(tag.getList("FoodHistory"));
	}
}
