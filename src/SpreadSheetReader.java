import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.*;;

/**
 * Helper class to read data from a spreadsheet.
 */
public class SpreadSheetReader {

    /**
     * Returns the desired information for a given restaurant as a string. Cannot return hours
     * @param rowNum the index of the restaurant as it appears in the xlsx file, from 1 to 30
     * @param type the type of information we want to get from the restaurant (name, cuisine, rating, range, address, or description)
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
        else if (type == "description") cell = row.getCell(12);
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

    
    public static void main(String[] args) throws IOException {
        double latitude = SpreadSheetReader.geoCode(55105, "latitude");
        double longitude = SpreadSheetReader.geoCode(55105, "longitude");
        System.out.println("Latitude: " + latitude + ". Longitude: " + longitude);
    }
}
