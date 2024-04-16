
import java.io.IOException;

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
    
    public GraphicsGroup makePage() throws IOException {
        
        int randomChoice = (int) ((Math.random() * (30 - 1)) + 1);

        GraphicsGroup page = new GraphicsGroup();

        GraphicsText name = new GraphicsText(SpreadSheetReader.getInfo(randomChoice, "name"));
        name.setCenter(canvasWidth/2, canvasHeight*0.2);
        page.add(name);

        //TODO: MAKE THIS SHOW STARS INSTEAD OF WORDS
        GraphicsText rating = new GraphicsText("Yelp Rating: " + SpreadSheetReader.getInfo(randomChoice, "rating"));
        rating.setCenter(canvasWidth/2, canvasHeight*0.3);
        page.add(rating);

        GraphicsText description = new GraphicsText(SpreadSheetReader.getInfo(randomChoice, "description"));
        description.setCenter(canvasWidth/2, canvasHeight*0.4);
        page.add(description);

        String[] openHours = SpreadSheetReader.getHours(randomChoice);
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

        GraphicsText address = new GraphicsText(SpreadSheetReader.getInfo(randomChoice, "address"));
        address.setCenter(canvasWidth/2, canvasHeight*0.95);
        page.add(address);

        return page;
    }
}
