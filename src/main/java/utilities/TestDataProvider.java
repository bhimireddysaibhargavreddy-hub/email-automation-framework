package utilities;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class TestDataProvider {

    @DataProvider(name = "testData")
    public static Object[][] getTestData(String testCaseName) {

        List<Map<String, String>> testDataList =
                new ArrayList<>();

        String filePath =
                "testdata/TestData.xlsx";

        try (Workbook workbook =
                     new XSSFWorkbook(filePath)) {

            Sheet sheet =
                    workbook.getSheet("TestData");

            DataFormatter formatter =
                    new DataFormatter();

            // Read headers
            Row headerRow =
                    sheet.getRow(0);

            List<String> headers =
                    new ArrayList<>();

            for (int i = 0;
                 i < headerRow.getLastCellNum();
                 i++) {

                String header =
                        formatter.formatCellValue(
                                headerRow.getCell(i)
                        ).trim();

                headers.add(header);
            }

            // Read each Excel row
            for (int rowIndex = 1;
                 rowIndex <= sheet.getLastRowNum();
                 rowIndex++) {

                Row row =
                        sheet.getRow(rowIndex);

                if (row == null) {
                    continue;
                }

                // Read TestCase from first column
                String currentTestCase =
                        formatter.formatCellValue(
                                row.getCell(0)
                        ).trim();

                // Take ONLY the requested test case
                if (currentTestCase.equalsIgnoreCase(
                        testCaseName)) {

                    Map<String, String> data =
                            new LinkedHashMap<>();

                    // Match headers with values
                    for (int columnIndex = 0;
                         columnIndex < headers.size();
                         columnIndex++) {

                        String header =
                                headers.get(columnIndex);

                        String value =
                                formatter.formatCellValue(
                                        row.getCell(columnIndex)
                                ).trim();

                        data.put(header, value);
                    }

                    testDataList.add(data);

                    // Stop after finding the required row
                    break;
                }
            }

        } catch (Exception e) {

            throw new RuntimeException(
                    "Unable to read TestData.xlsx",
                    e
            );
        }

        // Convert List<Map> into Object[][]
        Object[][] result =
                new Object[testDataList.size()][1];

        for (int i = 0;
             i < testDataList.size();
             i++) {

            result[i][0] =
                    testDataList.get(i);
        }

        return result;
    }
}