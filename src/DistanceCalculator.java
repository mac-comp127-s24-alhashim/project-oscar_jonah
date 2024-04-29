import java.io.IOException;

import edu.macalester.graphics.Point;

public class DistanceCalculator {
    /**
     * Calculates the distance between two zip codes
     * @param zipCodeOne a five-digit zip code
     * @param zipCodeTwo a five-digit zip code
     */
    public static double getDistance(double zipCodeOne, double zipCodeTwo) throws IOException {
        Point zipCodeOnePoint = new Point(SpreadSheetReader.geoCode(zipCodeOne, "latitude"), SpreadSheetReader.geoCode(zipCodeOne, "longitutde"));
        Point zipCodeTwoPoint = new Point(SpreadSheetReader.geoCode(zipCodeTwo, "latitude"), SpreadSheetReader.geoCode(zipCodeTwo, "longitutde"));

        double distance =  Math.sqrt(Math.pow((zipCodeOnePoint.getX() - zipCodeTwoPoint.getX()), 2) + Math.pow((zipCodeOnePoint.getY() - zipCodeTwoPoint.getY()), 2));      
        return distance;
    }
}
