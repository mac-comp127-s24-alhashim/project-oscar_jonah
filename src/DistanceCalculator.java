import java.io.IOException;

import edu.macalester.graphics.Point;

public class DistanceCalculator {
    public static double getDistance(Point zipCodeOne, Point zipCodeTwo) {
        double distance =  Math.sqrt(Math.pow((zipCodeOne.getX() - zipCodeTwo.getX()), 2) + Math.pow((zipCodeOne.getY() - zipCodeTwo.getY()), 2));      
        return distance;
    }
}
