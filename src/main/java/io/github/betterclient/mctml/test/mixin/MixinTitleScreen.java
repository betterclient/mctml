package io.github.betterclient.mctml.test.mixin;

import io.github.betterclient.mctml.test.Loader;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TitleScreen.class)
public class MixinTitleScreen extends Screen {
    private MixinTitleScreen() {
        super(Text.literal(""));
    }

    @Inject(method = "init", at = @At("RETURN"))
    public void init(CallbackInfo ci) {
        this.addDrawableChild(ButtonWidget
                .builder(Text.literal("Test HTML"), (button) -> Loader.start(0))
                .dimensions(this.width / 2 + 150, this.height / 4 + 48, 98, 20)
                .build()
        );
    }
}