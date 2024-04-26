import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.GraphicsText;
import edu.macalester.graphics.Image;
import edu.macalester.graphics.TextAlignment;
import edu.macalester.graphics.ui.Button;

/**
 * Final page that displays information about restaurant recommendation
 */

public class RestaurantRecommendationPage implements Page {
    double canvasWidth;
    double canvasHeight;
    RestaurantVisualization canvas;
    static int currentRest = 0;
    List<Restaurant> restList = List.of();

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
    
    public GraphicsGroup makePage(InputManager inputManager) throws IOException {

        if (this.restList.isEmpty() ) {
            restList = inputManager.getOrderedRestaurantList();
            if (restList.isEmpty()) {
                GraphicsGroup failPage = new GraphicsGroup();

                Image background = new Image("blank_options.jpg");
                failPage.add(background);

                GraphicsText failMessage = new GraphicsText("We could not find any restaurants that are\n in your budget for that cuisine.");
                failMessage.setAlignment(TextAlignment.CENTER);
                failMessage.setCenter(canvas.getCenter());

                failPage.add(failMessage);
                return failPage;

            }
        }
        

        return updatePage(restList, inputManager);
        
    }

    public static void resetPage() {
        currentRest = 0;
    }

    private GraphicsGroup updatePage(List<Restaurant> restList, InputManager inputManager) {
        Restaurant restaurantChoice = restList.get(currentRest);

        GraphicsGroup page = new GraphicsGroup();

        Image background = new Image("final_recommendation.jpg");
        page.add(background);

        Button nextPage = ButtonHelper.createButton(page, ">", canvas.getWidth()-30, canvas.getHeight()/2);
        nextPage.onClick(() -> {
            if (currentRest == restList.size()-1) {
                currentRest = 0;
            }
            else {
                currentRest += 1;
            }
            try {
                RestaurantFinder.getCanvas().setPage(4);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        Button prevPage = ButtonHelper.createButton(page, "<", 30, canvas.getHeight()/2);

        prevPage.onClick(() -> {
            if (currentRest == 0) {
                currentRest = restList.size();
            }
            else {
                currentRest -= 1;
            }
            try {
                RestaurantFinder.getCanvas().setPage(4);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        GraphicsText name = new GraphicsText(restaurantChoice.getName());
        name.setCenter(canvasWidth/2, canvasHeight*0.2);
        page.add(name);

        GraphicsText rating = new GraphicsText("Yelp Rating: " + restaurantChoice.getRating());
        rating.setCenter(canvasWidth/2, canvasHeight*0.3);
        page.add(rating);

        String descriptionRaw = restaurantChoice.getDescription();
        
        GraphicsText description = new GraphicsText(formatDescription(descriptionRaw));
        description.setAlignment(TextAlignment.CENTER); 
        description.setCenter(canvasWidth/2, canvasHeight*0.4);
        page.add(description);

        String[] openHours = restaurantChoice.getHours();
        GraphicsText mondayHours = new GraphicsText("Monday: " + openHours[0]);
        mondayHours.setCenter(canvasWidth/2, canvasHeight*0.6);
        page.add(mondayHours);
        GraphicsText tuesdayHours = new GraphicsText("Tuesday: " + openHours[1]);
        tuesdayHours.setCenter(canvasWidth/2, canvasHeight*0.6 + 20);
        page.add(tuesdayHours);
        GraphicsText wednesdayHours = new GraphicsText("Wednesday: " + openHours[2]);
        wednesdayHours.setCenter(canvasWidth/2, canvasHeight*0.6 + 40);
        page.add(wednesdayHours);
        GraphicsText thursdayHours = new GraphicsText("Thursday: " + openHours[3]);
        thursdayHours.setCenter(canvasWidth/2, canvasHeight*0.6 + 60);
        page.add(thursdayHours);
        GraphicsText fridayHours = new GraphicsText("Friday: " + openHours[4]);
        fridayHours.setCenter(canvasWidth/2, canvasHeight*0.6 + 80);
        page.add(fridayHours);
        GraphicsText saturdayHours = new GraphicsText("Saturday: " + openHours[5]);
        saturdayHours.setCenter(canvasWidth/2, canvasHeight*0.6 + 100);
        page.add(saturdayHours);
        GraphicsText sundayHours = new GraphicsText("Sunday: " + openHours[6]);
        sundayHours.setCenter(canvasWidth/2, canvasHeight*0.6 + 120);
        page.add(sundayHours);

        GraphicsText address = new GraphicsText(restaurantChoice.getLocation());
        address.setCenter(canvasWidth/2, canvasHeight*0.95);
        page.add(address);

        return page;
    }

    private String formatDescription(String description) {
        int spaceCount = 1;
        ArrayList<String> charList = new ArrayList<>();
        for (char character: description.toCharArray()) {
            if (character == ' ') {
                spaceCount += 1;
                if (spaceCount%7==0) {
                    charList.add("\n");
                }
                else {
                    charList.add(" ");
                }
            }
            else {
                charList.add(String.valueOf(character));
            }
        }
        String newString = "";

        for (String character: charList) {
            newString += character;
        }

        return newString;
    }
}
