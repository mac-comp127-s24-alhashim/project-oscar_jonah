import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.GraphicsText;
import edu.macalester.graphics.ui.Button;

/**
 * Landing page for user to either search for restaurant or get random recommendation
 */
public class HomePage implements Page {
    double canvasWidth;
    double canvasHeight;
    RestaurantVisualization canvas;

    public HomePage(RestaurantVisualization canvas) {
        this.canvasWidth = canvas.getWidth();
        this.canvasHeight = canvas.getHeight();
        this.canvas = canvas;
    }

    public GraphicsGroup makePage() {

        GraphicsGroup page = new GraphicsGroup();

        GraphicsText title = new GraphicsText("Where to eat?");
        title.setCenter(canvasWidth/2, canvasHeight*0.2);
        page.add(title);
        GraphicsText location = new GraphicsText("Twin Cities");
        location.setCenter(canvasWidth/2, canvasHeight*0.3);
        page.add(title);
        Button start = ButtonHelper.createButton(page, "Start", canvasWidth/2, canvasHeight*0.5);

        // TODO: Needs implementation
        Button randomChoice = ButtonHelper.createButton(page, "Give me a random restaurant", canvasWidth/2, canvasHeight*0.7); 

        //TODO: make setPage() method
        start.onClick(() -> canvas.setPage(1));
        randomChoice.onClick(() -> canvas.setPage(3));

        return page;
    }
}
