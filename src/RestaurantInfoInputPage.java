import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.GraphicsText;
import edu.macalester.graphics.ui.Button;
import edu.macalester.graphics.ui.TextField;

public class RestaurantInfoInputPage implements Page {
    double canvasWidth;
    double canvasHeight;
    RestaurantVisualization canvas;

    public RestaurantInfoInputPage(RestaurantVisualization canvas) {
        this.canvasWidth = canvas.getWidth();
        this.canvasHeight = canvas.getHeight();
        this.canvas = canvas;
    }
    /**
     * Page for user to input cuisine and budget
     */
    
    public GraphicsGroup makePage() {

        GraphicsGroup page = new GraphicsGroup();

        GraphicsText cuisinePrompt = new GraphicsText("Select a cuisine you are craving right now: ");
        cuisinePrompt.setCenter(canvasWidth/2, canvasHeight*0.2);
        page.add(cuisinePrompt);

        Button selectCuisine = ButtonHelper.createButton(page, "Select cuisine", canvasWidth/2, canvasHeight*0.3);
        selectCuisine.onClick(() -> ButtonHelper.createDropdownMenu(page, selectCuisine));

        GraphicsText budgetPrompt = new GraphicsText("Indicate your budget. If you don't have a budget in mind, leave blank.");
        budgetPrompt.setCenter(canvasWidth/2, canvasHeight*0.5);
        page.add(budgetPrompt);

        TextField budgetInput = new TextField();
        budgetInput.setCenter(canvasWidth/2, canvasHeight*0.6);
        page.add(budgetInput);

        Button nextPage = ButtonHelper.createButton(page, "Continue", canvasWidth/2, canvasHeight*0.8);
        
        nextPage.onClick(() -> canvas.setPage(3));
        
        return page;
    }
}
