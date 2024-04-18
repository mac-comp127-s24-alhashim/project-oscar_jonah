import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import edu.macalester.graphics.Point;

public class InputManager {
    private String cuisine;
    private int budget;
    private double startLocation;

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

    public List<Restaurant> getRestaurantList() throws IOException {
        ArrayList<Restaurant> relevantRestaurants = new ArrayList<Restaurant>();
        Point location = SpreadSheetReader.zipCodePoint(this.startLocation);

        int numOfRestaurants = SpreadSheetReader.getNumberOfRestaurants();

        for (int i=1; i<=numOfRestaurants; i++) {
            if ((SpreadSheetReader.getInfo(i,"cuisine").equals(this.cuisine)) || (this.cuisine.equals(""))) {
                if (this.budget == 0) {
                    relevantRestaurants.add(makeRestaurantFromRowNumber(i));
                }
                else if (Integer.parseInt((SpreadSheetReader.getInfo(i,"range").substring(1))) < this.budget) {
                    relevantRestaurants.add(makeRestaurantFromRowNumber(i));
                }
            }
        }

        // sorts list according to distance from start point
        List<Restaurant> orderedList = relevantRestaurants.stream()
            .sorted((i1,i2) -> {
                double i1ZipLat = 0;
                try {
                    i1ZipLat = SpreadSheetReader.geoCode(Double.parseDouble(i1.getZipCode()), "latitude");
                } catch (NumberFormatException | IOException e) {
                    e.printStackTrace();
                }
                double i1ZipLong = 0;
                try {
                    i1ZipLong = SpreadSheetReader.geoCode(Double.parseDouble(i1.getZipCode()), "longitude");
                } catch (NumberFormatException | IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                Point i1ZipPoint = new Point (i1ZipLat, i1ZipLong);
                Double distance1 = DistanceCalculator.getDistance(i1ZipPoint, location);
                
                double i2ZipLat = 0;
                try {
                    i2ZipLat = SpreadSheetReader.geoCode(Double.parseDouble(i2.getZipCode()), "latitude");
                } catch (NumberFormatException | IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                double i2ZipLong = 0;
                try {
                    i2ZipLong = SpreadSheetReader.geoCode(Double.parseDouble(i2.getZipCode()), "longitude");
                } catch (NumberFormatException | IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                Point i2ZipPoint = new Point (i2ZipLat, i2ZipLong);
                Double distance2 = DistanceCalculator.getDistance(i2ZipPoint, location);

                return distance1.compareTo(distance2);
            }).toList();

            return orderedList;
        }

    private Restaurant makeRestaurantFromRowNumber(int i) {
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
            String zipCode = "";
            try {
                zipCode = SpreadSheetReader.getInfo(i+1, "zip code");
            } catch (IOException e) {
                e.printStackTrace();
            }

            return new Restaurant(name, cuisine, rating, priceRange, hours, description, location, zipCode);
    }
    public static void main(String args[]) throws IOException {
        InputManager im = new InputManager();
        im.setBudget(40);
        im.setCuisine("American");
        im.setLocation("55105");
        System.out.println(im.getRestaurantList());
    }
}
