import java.io.IOException;

public class RestaurantFinder {
    static RestaurantVisualization viz;
    public static void main(String[] args) throws IOException {
        viz = new RestaurantVisualization();
    }

    public static RestaurantVisualization getCanvas() {
        return viz;
    }
    
}
