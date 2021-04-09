package testData;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ReadExcelFile {

    private static XSSFSheet excelWSheet;
    private static XSSFWorkbook excelWBook;

    public static Object[][] readExcel(String filePath, String sheetName) throws IOException {
        File file = new File(filePath);
        FileInputStream excelFile = new FileInputStream(file);
        excelWBook = new XSSFWorkbook(excelFile);
        excelWSheet = excelWBook.getSheet(sheetName);
        int rowCount = excelWSheet.getLastRowNum();
        int cellCount = excelWSheet.getRow(1).getLastCellNum();
        System.out.println(rowCount);
        System.out.println(cellCount);
        Object[][] testData = new Object[rowCount][cellCount];
        for (int i = 1; i < rowCount; i++){
            XSSFRow row = excelWSheet.getRow(i);
            for (int j = 0; j < cellCount; j++){
                XSSFCell cell = row.getCell(j);
                String data = cell.getStringCellValue();
                testData[i - 1][j] = data;
            }
        } return testData;
    }
}
