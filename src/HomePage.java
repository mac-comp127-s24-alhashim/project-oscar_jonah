import java.io.IOException;

import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.Image;
import edu.macalester.graphics.ui.Button;

/**
 * Landing page for user to either search for restaurant or get random recommendation
 */
public class HomePage implements Page {
    double canvasWidth;
    double canvasHeight;
    RestaurantVisualization canvas;

    /**
     * Landing page for user to either search for restaurant or get random recommendation
     * @param canvas extension of CanvasWindow, used to get dimensions and update visuals
     */
    public HomePage(RestaurantVisualization canvas) {
        this.canvasWidth = canvas.getWidth();
        this.canvasHeight = canvas.getHeight();
        this.canvas = canvas;
    }

    /**
     * Generates the home page
     * @param inputManager the inputManager for all the page classes
     */
    public GraphicsGroup makePage(InputManager inputManager) {

        GraphicsGroup page = new GraphicsGroup();

        Image background = new Image("start_page.jpg");
        page.add(background);

        Button start = ButtonHelper.createButton(page, "Start", canvasWidth/2, canvasHeight*0.5);

        Button randomChoice = ButtonHelper.createButton(page, "Give me a random restaurant", canvasWidth/2, canvasHeight*0.7); 

        start.onClick(() -> {
            try {
                canvas.setPage(1);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        randomChoice.onClick(() -> {
            try {
                canvas.setPage(3);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        return page;
    }
}
