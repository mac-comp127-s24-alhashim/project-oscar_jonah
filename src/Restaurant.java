public class Restaurant {
    private String name;
    private String cuisine;
    private double rating;
    private String priceRange;
    private String[] hours;
    private String description;
    private String location;

    /**
     * Class that contains restaurant attributes
     * @param name
     * @param cuisine
     * @param rating 
     * @param priceRange Typical price of meal
     * @param hours Array of seven Strings for daily hours, monday through sunday
     * @param description Short description of restaurant
     * @param location Address of restaurant
     */
    public Restaurant(String name, String cuisine, double rating, String priceRange, String[] hours, String description, String location) {
        this.name = name;
        this.cuisine = cuisine;
        this.rating = rating;
        this.priceRange = priceRange;
        this.hours = hours;
        this.description = description;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public String getCuisine() {
        return cuisine;
    }

    public double getRating() {
        return rating;
    }

    public String getPriceRange() {
        return priceRange;
    }

    public String[] getHours() {
        return hours;
    }

    public String getDescription() {
        return description;
    }

    public String getLocation() {
        return location;
    }
}
