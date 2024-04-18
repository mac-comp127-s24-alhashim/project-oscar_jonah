import java.awt.Color;
import java.io.IOException;

import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.GraphicsText;
import edu.macalester.graphics.Rectangle;
import edu.macalester.graphics.TextAlignment;
import edu.macalester.graphics.ui.Button;
import edu.macalester.graphics.ui.TextField;

/**
 * Page for user to input cuisine and budget
 */
public class RestaurantInfoInputPage implements Page {
    double canvasWidth;
    double canvasHeight;
    RestaurantVisualization canvas;
    InputManager inputManager;

    /**
     * Page for user to input cuisine and budget
     * @param canvas extension of CanvasWindow, used to get dimensions and update visuals
     */
    public RestaurantInfoInputPage(RestaurantVisualization canvas) {
        this.canvasWidth = canvas.getWidth();
        this.canvasHeight = canvas.getHeight();
        this.canvas = canvas;
    }

    /**
     * q1 displayed on makePage()
     * dropdown displayed when button clicked
     * q2 displayed after cuisine selected
     */
    public GraphicsGroup makePage(InputManager inputManager) {
        this.inputManager = inputManager;

        GraphicsGroup page = new GraphicsGroup();

        q1(page);
        
        return page;
    }

    private void q1(GraphicsGroup page) {
        GraphicsText cuisinePrompt = new GraphicsText("Select a cuisine you are craving right now, \nor select no preference: ");
        cuisinePrompt.setFontSize(18);
        cuisinePrompt.setAlignment(TextAlignment.CENTER);
        cuisinePrompt.setCenter(canvasWidth/2, canvasHeight*0.2);
        page.add(cuisinePrompt);

        Button selectCuisine = ButtonHelper.createButton(page, "Select cuisine", canvasWidth/2, canvasHeight*0.3);
        selectCuisine.onClick(() -> createDropdownMenu(page, selectCuisine));
    }

    public void q2(GraphicsGroup page) {
        GraphicsText budgetPrompt = new GraphicsText("Indicate your budget only numeric characters. \nIf you don't have a budget in mind, type 0.");
        budgetPrompt.setFontSize(18);
        budgetPrompt.setAlignment(TextAlignment.CENTER);
        budgetPrompt.setCenter(canvasWidth/2, canvasHeight*0.5);
        page.add(budgetPrompt);

        TextField budgetInput = new TextField();
        budgetInput.setCenter(canvasWidth/2, canvasHeight*0.6);
        page.add(budgetInput);

        Button nextPage = ButtonHelper.createButton(page, "Continue", canvasWidth/2, canvasHeight*0.8);
        
        nextPage.onClick(() -> {
            try {
                inputManager.setBudget(Integer.parseInt(budgetInput.getText()));
                canvas.setPage(2);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    /**
      * Creates a dropdown box where user can select cuisine preference
      * @param viz GraphicsGroup to add dropdown content to
      * @param mainButton "select cuisine" button, needed as parameter in order to replace it on canvas
      */
      public void createDropdownMenu(GraphicsGroup viz, Button mainButton) {
        String[] cuisines = {"American", "Italian", "Asian", "Mediterranean", "Mexican", "No preference"};
        GraphicsGroup dropdown  = new GraphicsGroup();
        Rectangle background = new Rectangle(0,0,0,0);
        background.setFillColor(Color.LIGHT_GRAY);
        
        // for each cuisine, creates a button that, when pressed, changes the prompt button to the selection and removes dropdown
        for (int i=0; i<cuisines.length; i++) {
            Button newChoice = new Button(cuisines[i]);
            newChoice.setCenter(dropdown.getCenter().getX(), i*30);
            dropdown.add(newChoice);
            String cuisineName = cuisines[i];
            newChoice.onClick(() -> {
                inputManager.setCuisine(cuisineName);
                GraphicsText cuisineSelection = new GraphicsText(cuisineName);
                cuisineSelection.setCenter(mainButton.getCenter());
                Rectangle border = new Rectangle(cuisineSelection.getX() - 7, cuisineSelection.getY() - 17, cuisineSelection.getWidth() + 14, cuisineSelection.getHeight() + 17);
                dropdown.removeAll();
                viz.add(border);
                viz.add(cuisineSelection);
                viz.remove(mainButton);
                q2(viz);
            });
        }
        
        background.setSize(dropdown.getSize());
        background.setCenter(dropdown.getCenter());
        dropdown.add(background);
        dropdown.setCenter(300, 500*0.35 + dropdown.getHeight()/2);
        viz.add(dropdown);
    }
}
