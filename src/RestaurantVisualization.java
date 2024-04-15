import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.ui.Button;

/**
 * Class that extends GraphicsGroup and creates the different navigation pages
 */
public class RestaurantVisualization extends CanvasWindow {
    double canvasWidth;
    double canvasHeight;
    private Page[] pages = new Page[4];

    /**
     * 
     * @param canvas takes canvas to get dimensions and add visualizations
     * @param page integer that designates which page is being displayed
     */
    public RestaurantVisualization() {
        super("Restaurant Finder", 600, 500);
        this.canvasWidth = this.getWidth();
        this.canvasHeight = this.getHeight();

        pages[0] = new HomePage(this);
        pages[1] = new RestaurantInfoInputPage(this);
        pages[2] = new LocationInputPage(this);
        pages[3] = new RestaurantRecommendationPage(this);

        setPage(0);
    }

    public void setPage(int page) {
        this.removeAll();
        if (page != 0) {
            this.add(addHomeButton());
        }
        this.add(pages[page].makePage());
    }

    private Button addHomeButton() {
        Button homeButton = new Button("Return to start");
        homeButton.setCenter(60, 15);
        homeButton.onClick(() -> setPage(0));
        return homeButton;
    }

}
