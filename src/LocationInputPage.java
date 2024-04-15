import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.GraphicsText;
import edu.macalester.graphics.ui.Button;
import edu.macalester.graphics.ui.TextField;

public class LocationInputPage implements Page {

    double canvasWidth;
    double canvasHeight;
    RestaurantVisualization canvas;


    public LocationInputPage(RestaurantVisualization canvas) {
        this.canvasWidth = canvas.getWidth();
        this.canvasHeight = canvas.getHeight();
        this.canvas = canvas;

    }

    public GraphicsGroup makePage() {

        GraphicsGroup page = new GraphicsGroup();

        GraphicsText locationPrompt = new GraphicsText("Where are you looking to eat?");
        locationPrompt.setCenter(canvasWidth/2, canvasHeight*0.35);
        page.add(locationPrompt);

        TextField locationInput = new TextField();
        locationInput.setCenter(canvasWidth/2, canvasHeight*0.6);
        page.add(locationInput);

        Button nextPage = ButtonHelper.createButton(page, "Continue", canvasWidth/2, canvasHeight*0.8);

        nextPage.onClick(() -> canvas.setPage(3));

        return page;
    }
}