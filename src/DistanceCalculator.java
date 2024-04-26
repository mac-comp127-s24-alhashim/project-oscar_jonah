import java.io.IOException;

import edu.macalester.graphics.Point;

public class DistanceCalculator {
    public static double getDistance(double zipCodeOne, double zipCodeTwo) throws IOException {
        Point zipCodeOnePoint = new Point(SpreadSheetReader.geoCode(zipCodeOne, "latitude"), SpreadSheetReader.geoCode(zipCodeOne, "longitutde"));
        Point zipCodeTwoPoint = new Point(SpreadSheetReader.geoCode(zipCodeTwo, "latitude"), SpreadSheetReader.geoCode(zipCodeTwo, "longitutde"));

        double distance =  Math.sqrt(Math.pow((zipCodeOnePoint.getX() - zipCodeTwoPoint.getX()), 2) + Math.pow((zipCodeOnePoint.getY() - zipCodeTwoPoint.getY()), 2));      
        return distance;
    }
}
