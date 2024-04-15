
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.GraphicsText;

/**
 * Final page that displays information about restaurant recommendation
 */
public class RestaurantRecommendationPage implements Page {
    double canvasWidth;
    double canvasHeight;
    RestaurantVisualization canvas;

    Restaurant restaurant;

    /**
     * Final page that displays information about restaurant recommendation
     * @param canvas extension of CanvasWindow, used to get dimensions
     */
    public RestaurantRecommendationPage(RestaurantVisualization canvas) {
        this.canvasWidth = canvas.getWidth();
        this.canvasHeight = canvas.getHeight();
        this.canvas = canvas;
    }
    // TODO: MAKE THIS USE ACTUAL INFORMATION
    
    public GraphicsGroup makePage() {
        
        GraphicsGroup page = new GraphicsGroup();

        GraphicsText name = new GraphicsText("Name");
        name.setCenter(canvasWidth/2, canvasHeight*0.2);
        page.add(name);

        //TODO: MAKE THIS SHOW STARS INSTEAD OF WORDS
        GraphicsText rating = new GraphicsText("Rating");
        rating.setCenter(canvasWidth/2, canvasHeight*0.3);
        page.add(rating);

        GraphicsText description = new GraphicsText("Description");
        description.setCenter(canvasWidth/2, canvasHeight*0.4);
        page.add(description);

        GraphicsText hours = new GraphicsText("Hours");
        hours.setCenter(canvasWidth/2, canvasHeight*0.5);
        page.add(hours);

        GraphicsText address = new GraphicsText("Address");
        address.setCenter(canvasWidth/2, canvasHeight*0.6);
        page.add(address);

        return page;
    }
}
