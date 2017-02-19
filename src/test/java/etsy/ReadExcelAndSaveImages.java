package etsy;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;

/**
 * Created by Rahul on 2017-02-18.
 */
public class ReadExcelAndSaveImages {

    public static void main(String[] args) throws IOException {

        File src = new File("src/test/resources/myfile_ COMPLETE LIST OF ALL ITEMS.xlsx");
        FileInputStream fis = new FileInputStream(src);
        XSSFWorkbook xwb = new XSSFWorkbook(fis);
        XSSFSheet sheet1 = xwb.getSheetAt(0);
        System.out.println("Total Physical Rows: " + sheet1.getPhysicalNumberOfRows());
        for (int iRow = 283;iRow< sheet1.getPhysicalNumberOfRows();iRow++){

            for (int iColumn = 0;iColumn<9;iColumn++){
                if (sheet1.getRow(iRow).getCell(iColumn).getStringCellValue().equals("-")){
                    break;
                }
                if (sheet1.getRow(iRow).getCell(iColumn).getStringCellValue().contains("http")){
                    URL imageURL = new URL(sheet1.getRow(iRow).getCell(iColumn).getStringCellValue());
                    BufferedImage saveImage = ImageIO.read(imageURL);
                    ImageIO.write(saveImage, "jpg", new File("src/test/images/" +iRow+"_" +iColumn +".jpg"));
                }
            //System.out.println("Current row " + iRow +
            //        " column = " +iColumn + " value = "
            //        + sheet1.getRow(iRow).getCell(iColumn).getStringCellValue());

            }
        }

    }










}
