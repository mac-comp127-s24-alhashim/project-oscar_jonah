import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.GraphicsText;
import edu.macalester.graphics.Rectangle;
import edu.macalester.graphics.ui.Button;
import edu.macalester.graphics.ui.TextField;

import java.awt.Color;

/**
 * Class that extends GraphicsGroup and creates the different navigation pages
 */
public class RestaurantVisualization extends GraphicsGroup {
    private int page;
    private CanvasWindow canvas;

    /**
     * 
     * @param canvas takes canvas to get dimensions and add visualizations
     * @param page integer that designates which page is being displayed
     */
    public RestaurantVisualization(CanvasWindow canvas, int page) {
        this.page = page;
        this.canvas = canvas;

        startPage();

        canvas.add(this);
    }

    private void returnHome() {
        Button homeButton = ButtonHelper.createButton(this, "Home", 35, 15);

        homeButton.onClick(() -> startPage());
    }

    /**
     * Landing page for user to either search for restaurant or get random recommendation
     */
    private void startPage() {
        this.removeAll();

        GraphicsText title = new GraphicsText("Where to eat?");
        title.setCenter(canvas.getWidth()/2, canvas.getHeight()*0.2);
        this.add(title);
        GraphicsText location = new GraphicsText("Twin Cities");
        location.setCenter(canvas.getWidth()/2, canvas.getHeight()*0.3);
        this.add(title);
        Button start = ButtonHelper.createButton(this, "Start", canvas.getWidth()/2, canvas.getHeight()*0.5);

        // TODO: Needs implementation
        Button randomChoice = ButtonHelper.createButton(this, "Give me a random restaurant", canvas.getWidth()/2, canvas.getHeight()*0.7); 

        start.onClick(() -> restaurantInfoInputPage());
        //randomChoice.onClick(() -> restaurantRecommendationPage(chooseRandomRestaurant()));
    }

    /**
     * Page for user to input cuisine and budget
     */
    private void restaurantInfoInputPage() {
        this.removeAll();
        returnHome();

        GraphicsText cuisinePrompt = new GraphicsText("Select a cuisine you are craving right now: ");
        cuisinePrompt.setCenter(canvas.getWidth()/2, canvas.getHeight()*0.2);
        this.add(cuisinePrompt);

        Button selectCuisine = ButtonHelper.createButton(this, "Select cuisine", canvas.getWidth()/2, canvas.getHeight()*0.3);
        selectCuisine.onClick(() -> ButtonHelper.createDropdownMenu(this, selectCuisine));

        GraphicsText budgetPrompt = new GraphicsText("Indicate your budget");
        budgetPrompt.setCenter(canvas.getWidth()/2, canvas.getHeight()*0.5);
        this.add(budgetPrompt);

        TextField budgetInput = new TextField();
        budgetInput.setCenter(canvas.getWidth()/2, canvas.getHeight()*0.6);
        this.add(budgetInput);

        Button nextPage = ButtonHelper.createButton(this, "Continue", canvas.getWidth()/2, canvas.getHeight()*0.8);
        nextPage.onClick(() -> locationInputPage());
    }


    private void locationInputPage() {
        this.removeAll();
        returnHome();

        GraphicsText locationPrompt = new GraphicsText("Where are you looking to eat?");
        locationPrompt.setCenter(canvas.getWidth()/2, canvas.getHeight()*0.35);
        this.add(locationPrompt);

        TextField locationInput = new TextField();
        locationInput.setCenter(canvas.getWidth()/2, canvas.getHeight()*0.6);
        this.add(locationInput);

        Button nextPage = ButtonHelper.createButton(this, "Continue", canvas.getWidth()/2, canvas.getHeight()*0.8);
        nextPage.onClick(() -> restaurantRecommendationPage());
    }

    // TODO: MAKE THIS USE ACTUAL INFORMATION
    private void restaurantRecommendationPage() {
        this.removeAll();
        returnHome();

        GraphicsText name = new GraphicsText("Name");
        name.setCenter(canvas.getWidth()/2, canvas.getHeight()*0.2);
        this.add(name);

        //TODO: MAKE THIS SHOW STARS INSTEAD OF WORDS
        GraphicsText rating = new GraphicsText("Rating");
        rating.setCenter(canvas.getWidth()/2, canvas.getHeight()*0.3);
        this.add(rating);

        GraphicsText description = new GraphicsText("Description");
        description.setCenter(canvas.getWidth()/2, canvas.getHeight()*0.4);
        this.add(description);

        GraphicsText hours = new GraphicsText("Hours");
        hours.setCenter(canvas.getWidth()/2, canvas.getHeight()*0.5);
        this.add(hours);

        GraphicsText address = new GraphicsText("Address");
        address.setCenter(canvas.getWidth()/2, canvas.getHeight()*0.6);
        this.add(address);
    }
}
