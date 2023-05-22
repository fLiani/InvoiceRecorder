import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class ExcelReader
{
    public ExcelReader()
    {

    }

    public Person readFile(File file) {
        try (Workbook workbook = WorkbookFactory.create(file))
        {
            Row rowTotal = workbook.getSheetAt(0).getRow(36);
            Cell cellTotal = rowTotal.getCell(4);
            Double total = Double.parseDouble(String.format("%.2f", cellTotal.getNumericCellValue()));


            Row rowName = workbook.getSheetAt(0).getRow(9);
            Cell cellName = rowName.getCell(0);
            String nameDetails = cellName.getStringCellValue();


            Row rowDate = workbook.getSheetAt(0).getRow(17);
            Cell cellDate = rowDate.getCell(4);
            Date dateVal = cellDate.getDateCellValue();
            LocalDate localDate = dateVal.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            String date = localDate.format(formatter);


            Row rowInv = workbook.getSheetAt(0).getRow(17);
            Cell cellInv = rowInv.getCell(0);
            int invNo = (int) cellInv.getNumericCellValue();


            Row rowVat = workbook.getSheetAt(0).getRow(35);
            Cell cellVat = rowVat.getCell(4);
            Double vat = Double.parseDouble(String.format("%.2f", cellVat.getNumericCellValue()));


            Row rowNet = workbook.getSheetAt(0).getRow(34);
            Cell cellNet = rowNet.getCell(4);
            double net = Double.parseDouble(String.format("%.2f", cellNet.getNumericCellValue()));

            return new Person(date, invNo, nameDetails, total, total, vat, net);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}