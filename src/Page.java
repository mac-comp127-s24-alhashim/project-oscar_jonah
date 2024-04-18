import java.io.IOException;

import edu.macalester.graphics.GraphicsGroup;

/**
 * Interface to create different pages with the same method, makePage()
 */
public interface Page {
    public GraphicsGroup makePage(InputManager inputManager) throws IOException;
}