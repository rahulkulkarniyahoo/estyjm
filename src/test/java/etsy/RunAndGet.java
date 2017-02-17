package etsy;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

/**
 * Created by rahul.kulkarni on 14/02/2017.
 */
public class RunAndGet {


    @Before
    public void myStart(){
        System.out.println("Started");
    }

    @After
    public void myTearDown(){
        System.out.println("Ended");
    }

    @Test
    public void myRun() throws IOException {
        System.out.println("Hello World");
        //Specify file path
        File src = new File("src/test/resources/myfile.xlsx");

        //Load the File
        FileInputStream fileInputStream = new FileInputStream(src);

        //Load the Workbook
        XSSFWorkbook wb = new XSSFWorkbook(fileInputStream);

        //Load the first sheet only.

        XSSFSheet sheet1 = wb.getSheetAt(0);

        System.out.println("Total Rows = " + sheet1.getPhysicalNumberOfRows());
        System.out.println("Total Columns = " + sheet1.getRow(0).getLastCellNum());
        System.out.println("_________________________________________________");

        //Reading Values
        for (int i=0;i<sheet1.getPhysicalNumberOfRows();i++){
            for (int j=0;j<sheet1.getRow(i).getLastCellNum();j++){
                System.out.println("Row =" + i + " Column = " + j + " Contains value = " + sheet1.getRow(i).getCell(j).getStringCellValue());
            }
        }
       int iJavlue = 4;
        //Writing values
        for (int i=0;i<sheet1.getPhysicalNumberOfRows();i++){
            for (int j=0;j<sheet1.getRow(i).getLastCellNum();j++) {
                sheet1.getRow(i).createCell(iJavlue).setCellValue("Hello " + i);
                FileOutputStream fout=new FileOutputStream(new File("src/test/resources/myfile2.xlsx"));
                wb.write(fout);
                fout.close();
            }
        }

        wb.close();
    }


    @Test
    public void myExcel(){

    }

}
