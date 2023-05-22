import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.*;

public class ExcelFileBuilder
{
    public ExcelFileBuilder()
    {

    }
    public void createExcelFile(String dbFilePath, String excelFilePath, JPanel panel)
    {
        JFileChooser chooser = new JFileChooser();
        int option = chooser.showOpenDialog(panel);
        File file = null;
        if (option == JFileChooser.APPROVE_OPTION)
        {
            file = chooser.getSelectedFile();
        }
        try {
            assert file != null;
            try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + file.getAbsolutePath());
                     Statement stmt = conn.createStatement();
                     ResultSet resultSet = stmt.executeQuery("SELECT * FROM Quarter")) {

                Workbook workbook = new XSSFWorkbook();
                Sheet sheet = workbook.createSheet("Quarter1");

                // Write column headers
                ResultSetMetaData metaData = resultSet.getMetaData();
                int columnCount = metaData.getColumnCount();
                Row headerRow = sheet.createRow(0);
                for (int i = 1; i <= columnCount; i++) {
                    Cell headerCell = headerRow.createCell(i - 1);
                    headerCell.setCellValue(metaData.getColumnName(i));
                }

                // Write data rows
                int rowNumber = 1;
                while (resultSet.next()) {
                    Row dataRow = sheet.createRow(rowNumber);
                    for (int i = 1; i <= columnCount; i++) {
                        Cell dataCell = dataRow.createCell(i - 1);
                        dataCell.setCellValue(resultSet.getString(i));
                    }
                    rowNumber++;
                }

                JFileChooser saveAs = new JFileChooser();
                int saveOption = chooser.showSaveDialog(panel);
                File saveFile = null;
                if (option == JFileChooser.APPROVE_OPTION)
                {
                    saveFile = chooser.getSelectedFile();
                }
                // Write the workbook to the Excel file
                try (FileOutputStream outputStream = new FileOutputStream(saveFile)) {
                    workbook.write(outputStream);
                }
                System.out.println("Excel file created successfully!");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
