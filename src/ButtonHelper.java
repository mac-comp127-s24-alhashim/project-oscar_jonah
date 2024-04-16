import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.ui.Button;

public class ButtonHelper {
    /**
     * Creates a new button and adds it to the canvas
     * @param viz the GraphicsGroup the button will be added to 
     * @param title the text on the button
     * @param x the x-coordinate of the button
     * @param y the y-coordinate of the button
     */
    public static Button createButton(GraphicsGroup viz, String title, double x, double y) {
        Button button = new Button(title);
        button.setCenter(x, y);
        viz.add(button);
        return button;
    }


     
}
