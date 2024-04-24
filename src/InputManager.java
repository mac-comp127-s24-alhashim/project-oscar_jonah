import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InputManager {
    private String cuisine;
    private int budget;
    private double startLocation;

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

    public void setLocation(String startLocation) {
        this.startLocation = Double.parseDouble(startLocation);
        System.out.println("Location: " + this.startLocation);
    }

    public List<Restaurant> getRestaurantList() throws IOException {
        ArrayList<Restaurant> relevantRestaurants = new ArrayList<Restaurant>();

        int numOfRestaurants = SpreadSheetReader.getNumberOfRestaurants();

        for (int i=1; i<=numOfRestaurants; i++) {
            if ((SpreadSheetReader.getInfo(i).get(1).equals(this.cuisine)) || (this.cuisine == null)) {
                if (this.budget == 0) {
                    relevantRestaurants.add(SpreadSheetReader.makeRestaurantFromRowNumber(i));
                }
                else if (Integer.parseInt((SpreadSheetReader.getInfo(i).get(10).substring(0,2))) < this.budget) {
                    relevantRestaurants.add(SpreadSheetReader.makeRestaurantFromRowNumber(i));
                }
            }
        } 

        // sorts list according to distance from start point
        List<Restaurant> orderedList = relevantRestaurants.stream() 
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
    
        return orderedList;
    }

    public static void main(String args[]) throws IOException {
        InputManager im = new InputManager();
        im.setBudget(15);
        im.setCuisine("American");
        im.setLocation("55104");
        List<Restaurant> restaurants = im.getRestaurantList();
        System.out.println(restaurants.size());
        for (Restaurant r : restaurants) {
            System.out.println(r.getName() + " " + r.getCuisine() + " " + r.getPriceRange());
        }

        // System.out.println(DistanceCalculator.getDistance(55105, 55104));
    }
}
