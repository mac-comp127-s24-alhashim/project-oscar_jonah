import java.io.IOException;

import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.GraphicsText;
import edu.macalester.graphics.Image;
import edu.macalester.graphics.TextAlignment;
import edu.macalester.graphics.ui.Button;
import edu.macalester.graphics.ui.TextField;

/**
 * Page for user to input prefered location of restaurant
 */
public class LocationInputPage implements Page {

    double canvasWidth;
    double canvasHeight;
    RestaurantVisualization canvas;

    /**
     * Page for user to input prefered location of restaurant
     * @param canvas extension of CanvasWindow, used to get dimensions and update visuals
     */
    public LocationInputPage(RestaurantVisualization canvas) {
        this.canvasWidth = canvas.getWidth();
        this.canvasHeight = canvas.getHeight();
        this.canvas = canvas;

    }

    public GraphicsGroup makePage(InputManager inputManager) {

        GraphicsGroup page = new GraphicsGroup();

        Image background = new Image("blank_options.jpg");
        page.add(background);

        GraphicsText locationPrompt = new GraphicsText("Where are you looking to eat? \n Input a 5-digit zip code:");
        locationPrompt.setFontSize(20);
        locationPrompt.setAlignment(TextAlignment.CENTER);
        locationPrompt.setCenter(canvasWidth/2, canvasHeight*0.35);
        page.add(locationPrompt);

        TextField locationInput = new TextField();
        locationInput.setCenter(canvasWidth/2, canvasHeight*0.6);
        page.add(locationInput);

        Button nextPage = ButtonHelper.createButton(page, "Continue", canvasWidth/2, canvasHeight*0.8);

        nextPage.onClick(() -> {
            try {
                inputManager.setLocation(locationInput.getText());
                canvas.setPage(4);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        return page;
    }
}
