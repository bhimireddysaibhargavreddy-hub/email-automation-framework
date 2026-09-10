package utilities;

import java.io.FileInputStream;
import java.io.IOException;

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

            Sheet sheet =
                    workbook.getSheet("Environment");

            Row headerRow =
                    sheet.getRow(0);

            Row dataRow =
                    sheet.getRow(1);

            DataFormatter formatter =
                    new DataFormatter();

            for (int i = 0;
                 i < headerRow.getLastCellNum();
                 i++) {

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
}
