package Excel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class writeExcel {

    public static void main(String[] args) {

        XSSFWorkbook workbook = new XSSFWorkbook();
        // Create a blank sheet
        XSSFSheet sheet = workbook.createSheet("Student Data");

        // Prepare data to be written as an Object[]
        Map<String, Object[]> data = new TreeMap<>();
        data.put("1", new Object[]{"ID", "NAME", "ROLLNO"});
        data.put("2", new Object[]{1, "Akash", 1001});
        data.put("3", new Object[]{2, "Pranav", 1002});
        data.put("4", new Object[]{3, "Vishal", 1003});
        data.put("5", new Object[]{4, "Sajid", 1004});

        // Iterate over data and write to the sheet
        Set<String> keyset = data.keySet();
        int rownum = 0;
        for (String key : keyset) {
            Row row = sheet.createRow(rownum++);
            Object[] objArr = data.get(key);
            int cellnum = 0;
            for (Object obj : objArr) {
                Cell cell = row.createCell(cellnum++);
                if (obj instanceof String) {
                    cell.setCellValue((String) obj);
                } else if (obj instanceof Integer) {
                    cell.setCellValue((Integer) obj);
                }
            }
        }

        // Write the workbook to the file system
        try {
            FileOutputStream out = new FileOutputStream(new File("StudentData.xlsx"));
            workbook.write(out);
            out.close();
            workbook.close(); // Close the workbook to prevent memory leaks
            System.out.println("StudentData written successfully on disk.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
