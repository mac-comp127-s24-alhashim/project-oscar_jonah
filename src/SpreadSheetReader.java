import java.io.FileInputStream;
import java.io.IOException;

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

     
    public static String getInfo(int rowNum, String type) throws IOException {
        String excelPathFile = "./res/RestaurantData.xlsx";
        FileInputStream inputStream = new FileInputStream(excelPathFile);
        XSSFCell cell;

        XSSFWorkbook wb = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = wb.getSheet("Restaurant_List");
        XSSFRow row = sheet.getRow(rowNum);
        if (type == "name") cell = row.getCell(0);
        else if (type == "cuisine") cell = row.getCell(1);
        else if (type == "rating") cell = row.getCell(2);
        else if (type == "range") cell = row.getCell(10);
        else if (type == "address") cell = row.getCell(11);
        else if (type == "zip code") cell = row.getCell(12);
        else if (type == "description") cell = row.getCell(13);
        else cell = null;
        wb.close();
        return cell.getStringCellValue();
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

    public static Restaurant makeRestaurantFromRowNumber(int i) {
        String name="";
            try {
                name = SpreadSheetReader.getInfo(i, "name");
            } catch (IOException e) {
                e.printStackTrace();
            }
            String cuisine="";
            try {
                cuisine = SpreadSheetReader.getInfo(i, "cuisine");
            } catch (IOException e) {
                e.printStackTrace();
            }
            double rating=-1;
            try {
                rating = Double.parseDouble(SpreadSheetReader.getInfo(i, "rating"));
            } catch (NumberFormatException | IOException e) {
                e.printStackTrace();
            }
            String priceRange="";
            try {
                priceRange = SpreadSheetReader.getInfo(i, "range");
            } catch (IOException e) {
                e.printStackTrace();
            }
            String location="";
            try {
                location = SpreadSheetReader.getInfo(i, "address");
            } catch (IOException e) {
                e.printStackTrace();
            }
            String description="";
            try {
                description = SpreadSheetReader.getInfo(i, "description");
            } catch (IOException e) {
                e.printStackTrace();
            }
            String[] hours = new String[7];
            try {
                hours = SpreadSheetReader.getHours(i);
            } catch (IOException e) {
                e.printStackTrace();
            }
            String zipCode = "";
            try {
                zipCode = SpreadSheetReader.getInfo(i, "zip code");
            } catch (IOException e) {
                e.printStackTrace();
            }

            return new Restaurant(name, cuisine, rating, priceRange, hours, description, location, zipCode);
    }
    
    public static void main(String[] args) throws IOException {
        // Point zipCodePoint = SpreadSheetReader.zipCodePoint(55105);
        // System.out.println("Longitude: " + zipCodePoint.getX() + ". Latitude: " + zipCodePoint.getY());
        // System.out.println(SpreadSheetReader.getNumberOfRestaurants());
        System.out.println(SpreadSheetReader.makeRestaurantFromRowNumber(1).getName());
    }
}
