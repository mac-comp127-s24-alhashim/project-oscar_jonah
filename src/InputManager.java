import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InputManager {
    private String cuisine = "No preference";
    private int budget;
    private double startLocation = 0;

    public InputManager() {

    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
        System.out.println("Cuisine: " + this.cuisine);
    }

    public void setBudget(int budget) {
        this.budget = budget;
        System.out.println("Budget: " + this.budget);
    }

    public List<Restaurant> getOrderedRestaurantList() throws IOException {
        return orderRestaurantList();
    }

    public List<Restaurant> getRestaurantList() throws IOException {
        return makeRestaurantList();
    }

    public void setLocation(String startLocation) {
        this.startLocation = Double.parseDouble(startLocation);
        System.out.println("Location: " + this.startLocation);
    }

    public void clearSelections() {
        startLocation = 0;
        cuisine = "No preference";
        budget = 0;
    }

    public String toString() {
        return "location: " + startLocation + ", cuisine: " + cuisine + ", budget: " + budget;
    }

    private List<Restaurant> makeRestaurantList() throws IOException {
        ArrayList<Restaurant> relevantRestaurants = new ArrayList<Restaurant>();

        int numOfRestaurants = SpreadSheetReader.getNumberOfRestaurants();

        for (int i=1; i<=numOfRestaurants; i++) {
            if ((SpreadSheetReader.getInfo(i).get(1).equals(this.cuisine)) || (this.cuisine == "No preference")) {
                if (this.budget == 0) {
                    relevantRestaurants.add(SpreadSheetReader.makeRestaurantFromRowNumber(i));
                }
                else if (Integer.parseInt((SpreadSheetReader.getInfo(i).get(10).substring(0,2))) < this.budget) {
                    relevantRestaurants.add(SpreadSheetReader.makeRestaurantFromRowNumber(i));
                }
            }
        }
        return relevantRestaurants; 
    }

    // sorts list according to distance from start point
    private List<Restaurant> orderRestaurantList() throws IOException {
        List<Restaurant> orderedList;
        if (this.startLocation != 0) {
            orderedList = makeRestaurantList().stream() 
            .sorted((i1,i2) -> {
                Double distance1 = 0.0;
                try {
                    distance1 = DistanceCalculator.getDistance(Double.parseDouble(i1.getZipCode()), this.startLocation);
                } catch (NumberFormatException | IOException e) {
                    e.printStackTrace();
                }
                Double distance2 = 0.0;
                try {
                    distance2 = DistanceCalculator.getDistance(Double.parseDouble(i2.getZipCode()), this.startLocation);
                } catch (NumberFormatException | IOException e) {
                    e.printStackTrace();
                }
                return distance1.compareTo(distance2);
            })
            .collect(Collectors.toList());
        }
        else {
            orderedList = makeRestaurantList();
        }

        return orderedList;
    }
     

    public static void main(String args[]) throws IOException {
        InputManager im = new InputManager();
        im.setBudget(15);
        im.setCuisine("American");
        im.setLocation("55104");
        List<Restaurant> restaurants = im.getOrderedRestaurantList();
        System.out.println(restaurants.size());
        for (Restaurant r : restaurants) {
            System.out.println(r.getName() + " " + r.getCuisine() + " " + r.getPriceRange());
        }

        // System.out.println(DistanceCalculator.getDistance(55105, 55104));
    }
}
