package io.github.betterclient.mctml.test;

import io.github.betterclient.jtml.api.elements.CanvasRenderingContext2D;
import io.github.betterclient.jtml.api.elements.HTMLCanvasElement;
import io.github.betterclient.jtml.api.elements.HTMLDocument;
import io.github.betterclient.jtml.api.util.DocumentScreenOptions;
import io.github.betterclient.mctml.MCTMLService;

public class Loader {
    /**
     * called from /test/1.html's body tag
     * {@link io.github.betterclient.jtml.internal.elements.HTMLDocument#setApi(HTMLDocument)}
     * @see <a href="https://github.com/jhy/jsoup/issues/2265">why this doesn't work</a>
     */
    public static void run(HTMLDocument document) {
        document.getElementById("hd").onMouseUp(event -> start(0));
        document.getElementById("cl").onMouseUp(event -> start(1));
        document.getElementById("ot").onMouseUp(event -> start(2));
    }

    public static void start(int c) {
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

        if (c == 2) {
            HTMLCanvasElement canvasElement = doc.getElementById("hello_world");

            CanvasRenderingContext2D context2D = canvasElement.getContext2D();

            context2D.clear();
            context2D.outlineRectangle(0, 0, canvasElement.getWidth(), canvasElement.getHeight());

            context2D.setColor("green");
            context2D.fillRectangle(15, 15, 15, 15);

            context2D.setColor("white");
            context2D.outlineRectangle(15, 15, 15, 15);

            context2D.setColor("rgb(255, 255, 0)");
            context2D.renderText("Text!", 120, 40);

            context2D.setColor("rgb(255, 0, 0)");
            context2D.setFontSize("15px");
            context2D.renderText("Text with size!", 10, 60);
        }

        doc.openAsScreen(new DocumentScreenOptions(true, true));
        System.out.println("Document took " + (System.currentTimeMillis() - start) + " milliseconds to open");
    }
}
