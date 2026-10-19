package qapy.spiceoflife.mixin;

import net.minecraft.client.gui.ButtonElement;
import net.minecraft.client.gui.Screen;
import net.minecraft.client.gui.container.ScreenInventory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ScreenInventory.class)
public class ScreenInventoryMixin {
	@Unique
	private ButtonElement foodButton;
	@Shadow
	protected int armourButtonFloatX;

	@Inject(method = "init", at = @At("TAIL"))
	public void spiceOfLife$addFoodButton(CallbackInfo ci) {
		foodButton = new ButtonElement(36,
			((Screen) (Object) this).width/2 - armourButtonFloatX - 41,
			((Screen) (Object) this).height/2 - 74,
			9,
			9,
			"")
			.setTextures("spiceoflife:gui/apple", "spiceoflife:gui/apple_highlight", "spiceoflife:gui/apple");
		((ScreenInventory) (Object) this).buttons.add(foodButton);
	}
	@Inject(method = "buttonClicked", at = @At("TAIL"))
	public void spiceOfLife$onFoodButtonClicked(ButtonElement button, CallbackInfo ci) {
		if (button == foodButton) {
			//todo
		}
	}
}
