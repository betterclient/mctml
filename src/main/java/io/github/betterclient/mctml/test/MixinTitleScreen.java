package io.github.betterclient.mctml.test;

import io.github.betterclient.jtml.api.DocumentScreenOptions;
import io.github.betterclient.jtml.api.elements.HTMLDocument;
import io.github.betterclient.mctml.MCTMLService;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
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
                .builder(Text.literal("Test HTML"), (button) -> start(0))
                .dimensions(this.width / 2 + 150, this.height / 4 + 48, 98, 20)
                .build()
        );
    }

    @Unique
    private void start(int c) {
        long start = System.currentTimeMillis();
        HTMLDocument doc = switch (c) {
            case 0 -> new HTMLDocument("/test/1.html", new MCTMLService());
            case 1 -> new HTMLDocument("/test/2.html", new MCTMLService());
            case 2 -> new HTMLDocument("/test/3.html", new MCTMLService());
            default -> throw new RuntimeException("a");
        };
        doc.getElementById("hd").onMouseUp(event -> start(0));
        doc.getElementById("cl").onMouseUp(event -> start(1));
        doc.getElementById("ot").onMouseUp(event -> start(2));

        doc.openAsScreen(new DocumentScreenOptions(true, true));
        System.out.println("Document took " + (System.currentTimeMillis() - start) + " milliseconds to open");
    }
}