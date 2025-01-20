package io.github.betterclient.mctml;

import io.github.betterclient.jtml.service.DocumentScreen;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class MCTMLDocumentRenderer extends Screen {
    public final DocumentScreen screen;

    protected MCTMLDocumentRenderer(DocumentScreen documentScreen) {
        super(Text.literal(""));
        this.screen = documentScreen;
    }

    @Override
    protected void init() {
        screen.init();
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        screen.render(
                new MCTMLRenderingService(context),
                () -> super.render(context, mouseX, mouseY, delta),
                delta,
                mouseX,
                mouseY
        );
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        screen.mouseClicked(mouseX, mouseY, button);

        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        screen.mouseReleased(mouseX, mouseY, button);

        return super.mouseReleased(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double horizontalAmount, double verticalAmount) {
        screen.mouseScroll(mouseX, mouseY, (int) verticalAmount);

        return super.mouseScrolled(mouseX, mouseY, horizontalAmount, verticalAmount);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        screen.keyPressed(keyCode, scanCode);

        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    public boolean keyReleased(int keyCode, int scanCode, int modifiers) {
        screen.keyReleased(keyCode, scanCode);

        return super.keyReleased(keyCode, scanCode, modifiers);
    }

    @Override
    public boolean charTyped(char chr, int modifiers) {
        screen.charTyped(chr);

        return super.charTyped(chr, modifiers);
    }
}