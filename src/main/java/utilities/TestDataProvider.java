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

public class TestDataProvider {

    private static final List<Map<String, String>> ALL_TEST_DATA =
            new ArrayList<>();

    static {

        String filePath = "testdata/TestData.xlsx";

        try (Workbook workbook =
                     new XSSFWorkbook(filePath)) {

            Sheet sheet =
                    workbook.getSheet("TestData");

            DataFormatter formatter =
                    new DataFormatter();

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

            for (int rowIndex = 1;
                 rowIndex <= sheet.getLastRowNum();
                 rowIndex++) {

                Row row =
                        sheet.getRow(rowIndex);

                if (row == null) {
                    continue;
                }

                Map<String, String> data =
                        new LinkedHashMap<>();

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

                ALL_TEST_DATA.add(data);
            }

        } catch (Exception e) {

            throw new RuntimeException(
                    "Unable to read TestData.xlsx",
                    e
            );
        }
    }

    public static Object[][] getTestData(String testCaseName) {

        List<Map<String, String>> matchingData =
                new ArrayList<>();

        for (Map<String, String> data : ALL_TEST_DATA) {

            String currentTestCase =
                    data.get("TestCase");

            if (currentTestCase != null
                    && currentTestCase.equalsIgnoreCase(
                            testCaseName)) {

                matchingData.add(data);
            }
        }

        Object[][] result =
                new Object[matchingData.size()][1];

        for (int i = 0;
             i < matchingData.size();
             i++) {

            result[i][0] =
                    matchingData.get(i);
        }

        return result;
    }
}