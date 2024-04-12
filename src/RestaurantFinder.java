import edu.macalester.graphics.CanvasWindow;

public class RestaurantFinder {
    CanvasWindow canvas = new CanvasWindow("Restaurant Visualization", 600, 500);

    private void run() {
        canvas.add(new RestaurantVisualization(canvas, 0));
        canvas.draw();
    }
    public static void main(String[] args) {
        RestaurantFinder restaurantFinder = new RestaurantFinder();

        restaurantFinder.run();
    }
}
