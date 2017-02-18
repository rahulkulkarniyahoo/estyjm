package etsy;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by Rahul on 2017-02-18.
 */
public class ReadExcelSheeet {

    public static void main(String[] args) throws IOException {

        File src = new File("src/test/resources/myfile.xlsx");
        FileInputStream fis = new FileInputStream(src);
        XSSFWorkbook xwb = new XSSFWorkbook(fis);
        XSSFSheet sheet1 = xwb.getSheetAt(0);

        System.out.println(sheet1.getRow(0).getCell(0).getStringCellValue());
        System.out.println(sheet1.getRow(0).getCell(1).getStringCellValue());

    }










}
