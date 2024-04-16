import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.*;;

/**
 * Helper class to read data from a spreadsheet.
 */
public class SpreadSheetReader {
    /**
     * Prints all of the data in a given spreadsheet
     */
    // private void printAll() throws IOException {
    //     String excelPathFile = "./res/RestaurantData.xlsx";
    //     FileInputStream inputStream = new FileInputStream(excelPathFile);

    //     XSSFWorkbook wb = new XSSFWorkbook(inputStream);
    //     XSSFSheet sheet = wb.getSheet("Restaurant_List");
    //     int rows = sheet.getLastRowNum();
    //     int columns = sheet.getRow(1).getLastCellNum();
    //     for (int i = 0; i < rows; i++) {
    //         XSSFRow row = sheet.getRow(i);
    //         for (int j = 0; j < columns; j++) {
    //             XSSFCell cell = row.getCell(j);
    //             switch(cell.getCellType()) {
    //                 case STRING: 
    //                 System.out.println(cell.getStringCellValue()); 
    //                 System.out.println("==================================="); break;
    //                 case NUMERIC: System.out.println(cell.getNumericCellValue()); 
    //                 System.out.println("==================================="); break;         
    //             }
    //         }
    //     }
    //     wb.close();
    // }

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

    
    // public static void main(String[] args) throws IOException {
    //     SpreadSheetReader reader = new SpreadSheetReader();
    //     // reader.printAll();
    //     String info = reader.getInfo(30, "rating");
    //     System.out.println(info);
    //     // String[] hours = reader.getHours(1);
    //     // for (String s : hours) {
    //     //     System.out.println(s);
    //     // }
    // }
}
