import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.*;;

public class SpreadSheetReader {
    private String getTitle() throws IOException {
        String excelPathFile = "./res/RestaurantData.xlsx";
        FileInputStream inputStream = new FileInputStream(excelPathFile);

        XSSFWorkbook wb = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = wb.getSheet("Restaurant_List");
        int rows = sheet.getLastRowNum();
        int columns = sheet.getRow(1).getLastCellNum();
        for (int i = 0; i < rows; i++) {
            XSSFRow row = sheet.getRow(i);
            for (int j = 0; j < columns; j++) {
                XSSFCell cell = row.getCell(j);
                switch(cell.getCellType()) {
                    case STRING: System.out.println(cell.getStringCellValue()); break;
                    case NUMERIC: System.out.println(cell.getNumericCellValue()); break;
                }
            }
        }
        return "";
    }
    public static void main(String[] args) throws IOException {
        SpreadSheetReader reader = new SpreadSheetReader();
        reader.getTitle();

    }
}
