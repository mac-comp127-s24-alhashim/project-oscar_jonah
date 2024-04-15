import java.util.ArrayList;
import java.util.HashMap;


public class RestaurantManager {
    private HashMap<String, Restaurant> nameToRestaurant;
    private ArrayList<String> restaurantList;
    
    /**
     * Creates storage space for restaurants
     */
    public RestaurantManager() {
        addFakeRestaurant();
    }

    public void addFakeRestaurant() {
        String[] hours = {"Closed",	"1600-2100", "1600-2100", "1600-2100", "1600-2200", "1600-2200", "1000-1400"};
        String description = "Gus Gus is a neighborhood bistro & bar located in St.Paul, MN serving eclectic small plates and elevated bar food alongside craft cocktails, wine & beer. (Google Maps)";
        String location = "128 Cleveland Ave N, St Paul, MN 55104";
        
        Restaurant restaurant = new Restaurant("Gus Gus", "American", 4.7, 50, hours, description, location);
        nameToRestaurant.put("Gus Gus", restaurant);
        restaurantList.add("Gus Gus");
    }

    public Restaurant getRestaurant(String restaurant) {
        return nameToRestaurant.get(restaurant);
    }
    public ArrayList<String> getRestaurantList() {
        return restaurantList;
    }

}
