package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

    private static final String EXCEL_PATH =
            "testdata/TestData.xlsx";

    private static final Map<String, String> ENVIRONMENT_DATA =
            new HashMap<>();

    static {

        try (FileInputStream fis =
                     new FileInputStream(EXCEL_PATH);
             Workbook workbook =
                     new XSSFWorkbook(fis)) {

            Sheet sheet =
                    workbook.getSheet("Environment");

            DataFormatter formatter =
                    new DataFormatter();

            Row headerRow =
                    sheet.getRow(0);

            Row dataRow =
                    sheet.getRow(1);

            for (int i = 0;
                 i < headerRow.getLastCellNum();
                 i++) {

                String header =
                        formatter.formatCellValue(
                                headerRow.getCell(i)
                        ).trim();

                String value =
                        formatter.formatCellValue(
                                dataRow.getCell(i)
                        ).trim();

                ENVIRONMENT_DATA.put(
                        header,
                        value
                );
            }

        } catch (IOException e) {

            throw new RuntimeException(
                    "Unable to read Excel file",
                    e
            );
        }
    }

    public static String getEnvironmentData(
            String columnName) {

        String value =
                ENVIRONMENT_DATA.get(columnName);

        if (value == null) {

            throw new RuntimeException(
                    "Column not found in Environment sheet: "
                            + columnName
            );
        }

        return value;
    }
}