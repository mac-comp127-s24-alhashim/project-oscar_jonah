import java.awt.Color;

import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.GraphicsText;
import edu.macalester.graphics.Rectangle;
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

    /**
     * Creates a dropdown box where user can select cuisine preference
     * @param mainButton select cuisine button, needed as parameter in order to replace it on canvas
     */
    public static void createDropdownMenu(GraphicsGroup viz, Button mainButton) {
        String[] cuisines = {"American", "Italian", "Asian", "Mediterranean", "Mexican", "I don't care"};
        GraphicsGroup dropdown  = new GraphicsGroup();
        Rectangle background = new Rectangle(0,0,0,0);
        background.setFillColor(Color.LIGHT_GRAY);
        
        // for each cuisine, creates a button that, when pressed, changes the prompt button to the selection and removes dropdown
        for (int i=0; i<cuisines.length; i++) {
            Button newChoice = new Button(cuisines[i]);
            newChoice.setCenter(dropdown.getCenter().getX(), i*30);
            dropdown.add(newChoice);
            String cuisineName = cuisines[i];
            newChoice.onClick(() -> {
                GraphicsText cuisineSelection = new GraphicsText(cuisineName);
                cuisineSelection.setCenter(mainButton.getCenter());
                dropdown.removeAll();
                viz.add(cuisineSelection);
                viz.remove(mainButton);
            });
        }
        
        background.setSize(dropdown.getSize());
        background.setCenter(dropdown.getCenter());
        dropdown.add(background);
        dropdown.setCenter(300, 500*0.35 + dropdown.getHeight()/2);
        viz.add(dropdown);
    }
}
