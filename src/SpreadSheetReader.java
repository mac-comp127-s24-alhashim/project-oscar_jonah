import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.*;

import edu.macalester.graphics.Point;;

/**
 * Helper class to read data from a spreadsheet.
 */
public class SpreadSheetReader {

    /**
     * Returns the desired information for a given restaurant as a string. Cannot return hours
     * @param rowNum the index of the restaurant as it appears in the xlsx file, from 1 to 30
     * @param type the type of information we want to get from the restaurant (name, cuisine, rating, range, address, zip code, or description)
     */
    public static List<String> getInfo(int rowNum) throws IOException {
        List<String> information = new ArrayList<>();
        String excelPathFile = "./res/RestaurantData.xlsx";
        FileInputStream inputStream = new FileInputStream(excelPathFile);
        XSSFCell cell;

        XSSFWorkbook wb = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = wb.getSheet("Restaurant_List");
        XSSFRow row = sheet.getRow(rowNum);

        for (int i = 0; i < 14; i++) {
            cell = row.getCell(i);
            information.add(cell.getStringCellValue());
        }
        wb.close();
        return information;
    }

    /**
     * Returns the open/closed hours for a given restaurant as an array of strings
     * @param rowNum the index of the restaurant as it appears in the xlsx file, from 1 to 30
     */
    public static String[] getHours(int rowNum) throws IOException {
        String[] hours = new String[7];
        String excelPathFile = "./res/RestaurantData.xlsx";
        FileInputStream inputStream = new FileInputStream(excelPathFile);
        XSSFCell cell;

        XSSFWorkbook wb = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = wb.getSheet("Restaurant_List");
        XSSFRow row = sheet.getRow(rowNum);

        for (int i = 0; i < hours.length; i++) {
            cell = row.getCell(i+3);
            hours[i] = cell.getStringCellValue();
        }
        wb.close();
        return hours;
    }

    /**
     * Returns the latitude or longitude of the given zip code
     * @param zipCode the input zip code
     * @param latOrLong a string input to indicate the desired value. Either "latitude" or "longitude"
     * @throws IOException 
     */
    public static double geoCode(double zipCode, String latOrLong) throws IOException {
        String excelPathFile = "./res/TwinCitiesZipCodes.xlsx";
        FileInputStream inputStream = new FileInputStream(excelPathFile);
        double value = 0;

        XSSFWorkbook wb = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = wb.getSheet("Zip_Codes");
        for (int i = 1; i < 84; i++) {
            Row row = sheet.getRow(i);
            Cell cell = row.getCell(0);
            if (cell.getNumericCellValue() == zipCode) {
                if (latOrLong == "latitude") {
                    cell = row.getCell(1);
                    value = cell.getNumericCellValue();
                }
                else if (latOrLong == "longitude") {
                    cell = row.getCell(2);
                    value = cell.getNumericCellValue();
                }
                
            }
        }
        wb.close();
        return value;
    }

    /**
     * Returns a point with the zip code values of (longitude, latitude)
     * @param zipCode the input zip code
     * @throws IOException 
     */
    public static Point zipCodePoint(double zipCode) throws IOException {
        double latitude = SpreadSheetReader.geoCode(zipCode, "latitude");
        double longitude = SpreadSheetReader.geoCode(zipCode, "longitude");
        return new Point(longitude, latitude);
    }

    /**
     * Returns the total number of restaurants in the spreadsheet
     * @throws IOException 
     */
    public static int getNumberOfRestaurants() throws IOException {
        String excelPathFile = "./res/RestaurantData.xlsx";
        FileInputStream inputStream = new FileInputStream(excelPathFile);

        XSSFWorkbook wb = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = wb.getSheet("Restaurant_List");

        int count = 0;
        
        for (Row row : sheet) {
            Cell cell = row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
            String restaurantName = cell.getStringCellValue().trim();
            if (!restaurantName.isEmpty()) {
                count++;
            }
        }

        wb.close();
        return count - 1;
    }

    /**
     * Returns the desired restaurant based on its index in the spreadsheet
     * @param i the index of the restaurant in the spreadsheet
     */
    public static Restaurant makeRestaurantFromRowNumber(int i) throws IOException {
        List<String> restInfo = getInfo(i);
        String name = restInfo.get(0);
        String cuisine = restInfo.get(1);
        double rating= Double.parseDouble(restInfo.get(2));
        String priceRange = restInfo.get(10);
        String location= restInfo.get(11);
        String description = restInfo.get(13);
        String[] hours = new String[7];
        hours = SpreadSheetReader.getHours(i);
        String zipCode = restInfo.get(12);

        return new Restaurant(name, cuisine, rating, priceRange, hours, description, location, zipCode);
    }
}
