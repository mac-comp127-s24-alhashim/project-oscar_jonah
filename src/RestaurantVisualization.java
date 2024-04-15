
import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.ui.Button;

import java.awt.Color;

/**
 * Class that extends GraphicsGroup and creates the different navigation pages
 */
public class RestaurantVisualization extends CanvasWindow {
    double canvasWidth;
    double canvasHeight;
    private Page[] pages = new Page[4];

    /**
     * Class that extends GraphicsGroup and creates the different navigation pages
     * @param canvas takes canvas to get dimensions and add visualizations
     * @param page integer that designates which page is being displayed
     */
    public RestaurantVisualization() {
        super("Restaurant Finder", 600, 500);
        this.setBackground(Color.PINK);
        this.canvasWidth = this.getWidth();
        this.canvasHeight = this.getHeight();

        pages[0] = new HomePage(this);
        pages[1] = new RestaurantInfoInputPage(this);
        pages[2] = new LocationInputPage(this);
        pages[3] = new RestaurantRecommendationPage(this);

        setPage(0);
    }

    /**
     * Changes which page is displayed on CanvasWindow
     * @param page index of page in Array pages, used to select which page to display
     */
    public void setPage(int page) {
        this.removeAll();
        if (page != 0) {
            addHomeButton();
        }
        this.add(pages[page].makePage());
    }

    /**
     * Adds button to canvas to return to first page
     */
    private void addHomeButton() {
        Button homeButton = new Button("Return to start");
        homeButton.setCenter(65, 15);
        homeButton.onClick(() -> setPage(0));
        this.add(homeButton);
    }

}
