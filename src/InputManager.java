import java.io.IOException;
import java.util.ArrayList;

import edu.macalester.graphics.Point;

public class InputManager {
    private String cuisine;
    private int budget;
    private double startLocation;
    private static Restaurant[] listOfRestaurants;

    public InputManager() {

    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public void setLocation(String startLocation) {
        this.startLocation = Double.parseDouble(startLocation);
    }

    private void makeListOfRestaurants() {
        //int numOfRestaurants = SpreadSheetReader.getRestaurantNumber();
        int numOfRestaurants = 30;
        Restaurant[] listOfRestaurants = new Restaurant[numOfRestaurants];

        for (int i=0; i<numOfRestaurants; i++) {
            String name="";
            try {
                name = SpreadSheetReader.getInfo(i+1, "name");
            } catch (IOException e) {
                e.printStackTrace();
            }
            String cuisine="";
            try {
                cuisine = SpreadSheetReader.getInfo(i+1, "cuisine");
            } catch (IOException e) {
                e.printStackTrace();
            }
            double rating=-1;
            try {
                rating = Double.parseDouble(SpreadSheetReader.getInfo(i+1, "rating"));
            } catch (NumberFormatException | IOException e) {
                e.printStackTrace();
            }
            String priceRange="";
            try {
                priceRange = SpreadSheetReader.getInfo(i+1, "range");
            } catch (IOException e) {
                e.printStackTrace();
            }
            String location="";
            try {
                location = SpreadSheetReader.getInfo(i+1, "address");
            } catch (IOException e) {
                e.printStackTrace();
            }
            String description="";
            try {
                description = SpreadSheetReader.getInfo(i+1, "description");
            } catch (IOException e) {
                e.printStackTrace();
            }
            String[] hours = new String[7];
            try {
                hours = SpreadSheetReader.getHours(i+1);
            } catch (IOException e) {
                e.printStackTrace();
            }

            listOfRestaurants[i] = new Restaurant(name, cuisine, rating, priceRange, hours, description, location);
        }
        this.listOfRestaurants = listOfRestaurants;
    }

    public void getRestaurant() throws IOException {
        ArrayList<Restaurant> relevantRestaurants = new ArrayList<Restaurant>();
        Point location = SpreadSheetReader.zipCodePoint(this.startLocation);
        
        for (Restaurant r: listOfRestaurants) {
            if ((r.getCuisine().equals(this.cuisine)) || (this.cuisine.equals(""))) {
                if (this.budget == 0) {
                    relevantRestaurants.add(r);
                }
                else if (Integer.parseInt((r.getPriceRange().substring(1))) < this.budget) {
                    relevantRestaurants.add(r);
                }
            }
        }

        //TODO: COMPARE DISTANCES AND SUGGEST CLOSEST RESTAURANT
    }


}
