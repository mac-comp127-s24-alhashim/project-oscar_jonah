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
        Button homeButton = new Button("Home");
        homeButton.setCenter(35, 15);
        this.add(homeButton);

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
        Button start = new Button("Search for a restaurant");
        start.setCenter(canvas.getWidth()/2, canvas.getHeight()*0.5);
        this.add(start);
        Button randomChoice = new Button("Give me a random restaurant");
        randomChoice.setCenter(canvas.getWidth()/2, canvas.getHeight()*0.7);
        this.add(randomChoice);

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

        Button selectCuisine = new Button("Select cuisine");
        selectCuisine.setCenter(canvas.getWidth()/2, canvas.getHeight()*0.3);
        this.add(selectCuisine);
        selectCuisine.onClick(() -> dropdownMenu(selectCuisine));

        GraphicsText budgetPrompt = new GraphicsText("Indicate your budget");
        budgetPrompt.setCenter(canvas.getWidth()/2, canvas.getHeight()*0.5);
        this.add(budgetPrompt);

        TextField budgetInput = new TextField();
        budgetInput.setCenter(canvas.getWidth()/2, canvas.getHeight()*0.6);
        this.add(budgetInput);

        Button nextPage = new Button("Continue");
        nextPage.setCenter(canvas.getWidth()/2, canvas.getHeight()*0.8);
        this.add(nextPage);
        nextPage.onClick(() -> locationInputPage());
    }

    /**
     * Creates a dropdown box where user can select cuisine preference
     * @param mainButton select cuisine button, needed as parameter in order to replace it on canvas
     */
    private void dropdownMenu(Button mainButton) {
        String[] cuisines = {"American", "Italian", "Asian", "Mediterranean", "Mexican"};
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
                GraphicsText cuisineSelection = new GraphicsText(cuisineName);
                cuisineSelection.setCenter(mainButton.getCenter());
                dropdown.removeAll();
                this.add(cuisineSelection);
                this.remove(mainButton);
            });
        }
        
        background.setSize(dropdown.getSize());
        background.setCenter(dropdown.getCenter());
        dropdown.add(background);
        dropdown.setCenter(canvas.getWidth()/2, canvas.getHeight()*0.35 + dropdown.getHeight()/2);
        this.add(dropdown);
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

        Button nextPage = new Button("Continue");
        nextPage.setCenter(canvas.getWidth()/2, canvas.getHeight()*0.8);
        this.add(nextPage);
        nextPage.onClick(() -> restaurantRecommendationPage());
    }

    // TO DO: MAKE THIS USE ACTUAL INFORMATION
    private void restaurantRecommendationPage() {
        this.removeAll();
        returnHome();

        GraphicsText name = new GraphicsText("Name");
        name.setCenter(canvas.getWidth()/2, canvas.getHeight()*0.2);
        this.add(name);

        //TO DO: MAKE THIS SHOW STARS INSTEAD OF WORDS
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
