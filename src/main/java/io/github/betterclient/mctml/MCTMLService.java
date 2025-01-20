package io.github.betterclient.mctml;

import io.github.betterclient.jtml.service.DocumentScreen;
import io.github.betterclient.jtml.service.JTMLService;
import io.github.betterclient.jtml.service.RenderingService;
import io.github.betterclient.jtml.service.UtilityService;
import net.minecraft.client.MinecraftClient;

public class MCTMLService implements JTMLService {
    @Override
    public RenderingService getFontService() {
        return MCTMLRenderingService.DEFAULT_FONT;
    }

    @Override
    public UtilityService getUtilityService() {
        return MCTMLUtilityService.INSTANCE;
    }

    @Override
    public void openScreen(DocumentScreen documentScreen) {
        MinecraftClient.getInstance().setScreen(new MCTMLDocumentRenderer(documentScreen));
    }
}
