package utilities;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

    private static final String EXCEL_PATH =
    		  "testdata/TestData.xlsx";

    // Read Environment sheet
    public static String getEnvironmentData(String columnName) {

        try (FileInputStream fis =
                     new FileInputStream(EXCEL_PATH);
             Workbook workbook =
                     new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet("Environment");

            Row headerRow = sheet.getRow(0);
            Row dataRow = sheet.getRow(1);

            DataFormatter formatter = new DataFormatter();

            for (int i = 0; i < headerRow.getLastCellNum(); i++) {

                String header =
                        formatter.formatCellValue(
                                headerRow.getCell(i)
                        ).trim();

                if (header.equalsIgnoreCase(columnName)) {

                    return formatter.formatCellValue(
                            dataRow.getCell(i)
                    ).trim();
                }
            }

            throw new RuntimeException(
                    "Column not found in Environment sheet: "
                            + columnName
            );

        } catch (IOException e) {

            throw new RuntimeException(
                    "Unable to read Excel file",
                    e
            );
        }
    }

    // Read TestData sheet
    public static String getTestData(
            String testCase,
            String columnName) {

        try (FileInputStream fis =
                     new FileInputStream(EXCEL_PATH);
             Workbook workbook =
                     new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet("TestData");

            Row headerRow = sheet.getRow(0);

            DataFormatter formatter =
                    new DataFormatter();

            int testCaseColumn = -1;
            int dataColumn = -1;

            // Find TestCase and requested column
            for (int i = 0;
                 i < headerRow.getLastCellNum();
                 i++) {

                String header =
                        formatter.formatCellValue(
                                headerRow.getCell(i)
                        ).trim();

                if (header.equalsIgnoreCase("TestCase")) {
                    testCaseColumn = i;
                }

                if (header.equalsIgnoreCase(columnName)) {
                    dataColumn = i;
                }
            }

            if (testCaseColumn == -1) {
                throw new RuntimeException(
                        "TestCase column not found"
                );
            }

            if (dataColumn == -1) {
                throw new RuntimeException(
                        "Column not found: " + columnName
                );
            }

            // Search for the requested test case
            for (int rowIndex = 1;
                 rowIndex <= sheet.getLastRowNum();
                 rowIndex++) {

                Row row = sheet.getRow(rowIndex);

                String currentTestCase =
                        formatter.formatCellValue(
                                row.getCell(testCaseColumn)
                        ).trim();

                if (currentTestCase.equalsIgnoreCase(testCase)) {

                    Cell cell =
                            row.getCell(dataColumn);

                    if (cell == null) {
                        return "";
                    }

                    return formatter
                            .formatCellValue(cell)
                            .trim();
                }
            }

            throw new RuntimeException(
                    "TestCase not found: " + testCase
            );

        } catch (IOException e) {

            throw new RuntimeException(
                    "Unable to read Excel file",
                    e
            );
        }
    }
}