package io.github.betterclient.mctml;

import io.github.betterclient.jtml.service.UtilityService;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.resource.language.I18n;
import org.lwjgl.glfw.GLFW;

public class MCTMLUtilityService implements UtilityService {
    public static final UtilityService INSTANCE = new MCTMLUtilityService();

    @Override
    public String translate(String s) {
        return I18n.translate(s);
    }

    @Override
    public String getKeyName(int i, int i1) {
        return GLFW.glfwGetKeyName(i, i1);
    }

    @Override
    public int getSpaceKeyCode() {
        return GLFW.GLFW_KEY_SPACE;
    }

    @Override
    public boolean isControlDown() {
        return Screen.hasControlDown();
    }
}
