package pages;


/**
 * Created by rahul.kulkarni on 14/02/2017.
 */



import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.DefaultUrl;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleContains;

//@DefaultUrl("https://www.etsy.com/uk/shop/CanvasVows")
@DefaultUrl("https://www.etsy.com/uk/shop/JeenMata")
public class UserMainPage extends PageObject{

   // https://www.etsy.com/uk/shop/CanvasVows


    @FindBy(xpath=".//*[@id='items']//a[@href='/uk/shop/JeenMata/items#fromHome']")
    WebElement buttonViewAllItems;

    @FindBy(xpath="//span[@itemprop='name']")
    WebElement textTitle;

    @FindBy(xpath="//*[@id='listing-price']")
    WebElement textListingPrice;


    @FindBy(xpath="//*[@id='description-text']")
    WebElement textDescriptionText;


    public void clickViewAllItems() {
        buttonViewAllItems.click();
        waitFor(titleContains("Your"));
    }

    public void listAllItems() throws InterruptedException, IOException {
        //List of preceeding elements
        List<WebElement> iListOnPage = getDriver().findElements(By.xpath("//*[@id='items']/div/div[3]/div[2]/div/*"));

        //Display total list size of all products
        System.out.println("iList.size() = " + iListOnPage.size());
        int iColumnNumber = 0;
        int iIntRow = 5;
        int iPageNumber = 5;

     //   for (int iPageNumber = 2;iPageNumber <=5; iPageNumber++){
            getDriver().get("https://www.etsy.com/uk/shop/JeenMata/items?ref=pagination&page=" + iPageNumber);

            //Navigate through all Product Images. replace 3 with iListOnPage.size()
            for (int i = 6; i < iListOnPage.size(); i++) {
                try {
                File src = new File("src/test/resources/myfile.xlsx");

                //Load the File
                FileInputStream fileInputStream = new FileInputStream(src);

                //Load the Workbook
                XSSFWorkbook wb = new XSSFWorkbook(fileInputStream);

                //Load the first sheet only.
                XSSFSheet sheet1 = wb.getSheetAt(0);

                //Click on each item
                getDriver().findElement(By.xpath("//*[@id='items']/div/div[3]/div[2]/div/div[" + i + "]/a/div/div[1]")).click();

                Thread.sleep(3000);

                //Get List of all image elements
                WebElement we = getDriver().findElement(By.xpath("//*[@id='circles']"));

                //Get list of all thumbnail size images
                List<WebElement> iList = we.findElements(By.cssSelector(".thumbnail-nav img"));
                System.out.println("iList.size() = " + iList.size());


                // Confusion on what to write here to put values into the excel sheet
                //Writing to Excel File

                //Get Title
                System.out.println(getDriver().findElement(By.xpath("//div[@id='listing-page-cart-inner']/h1/span")).getText());

                //Set Title
                sheet1.getRow(iIntRow).createCell(iColumnNumber + 1).setCellValue(getDriver().findElement(By.xpath("//div[@id='listing-page-cart-inner']/h1/span")).getText());
                // sheet1.getRow(5).createCell(5).setCellValue(tempValue);

                //Get Price
                System.out.println(getDriver().findElement(By.id("listing-price")).getText());

                //Set Title
                sheet1.getRow(iIntRow).createCell(iColumnNumber + 2).setCellValue(getDriver().findElement(By.id("listing-price")).getText());

                //Get Description
                System.out.println(getDriver().findElement(By.id("description-text")).getText());

                //Set Description
                sheet1.getRow(iIntRow).createCell(iColumnNumber + 3).setCellValue(getDriver().findElement(By.id("description-text")).getText());

                int iNewColumnValue = iColumnNumber + 4;
                //Set Images
                for (int k = 0; k < iList.size(); k++) {
                    System.out.println("This is k = " + k + " = " + iList.get(k).getAttribute("src"));
                    sheet1.getRow(iIntRow).createCell(iNewColumnValue++).setCellValue(iList.get(k).getAttribute("src"));
                }

                FileOutputStream fout = new FileOutputStream(new File("src/test/resources/myfile.xlsx"));
                wb.write(fout);
                fout.close();
                wb.close();
                iIntRow++;
                System.out.println("STOP");

                getDriver().navigate().back();
                Thread.sleep(4000);
                }catch (NoSuchElementException e) {
                    //Do Nothing
                    break;
                }
            }
            //write catch here


        }

    }

