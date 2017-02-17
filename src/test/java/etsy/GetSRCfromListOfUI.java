package etsy;

import net.serenitybdd.core.annotations.findby.By;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rahul.kulkarni on 16/02/2017.
 */
public class GetSRCfromListOfUI {

    FirefoxDriver driver;
    @Before
    public void startUp(){
        System.out.println("Started");
        System.setProperty("webdriver.gecko.driver", "src/test/resources/geckodriver");
        System.out.println(" ********** Setting Property - Done *********");
        driver = new FirefoxDriver();
        driver.get("https://www.etsy.com/uk/listing/465419784/printed-silk-kimono-top-blur");
    }

    @Test
    public void myGetSRC(){
        System.out.println("Test begins");
        System.out.println(driver.getTitle());

        WebElement we = driver.findElement(By.xpath("//*[@id='circles']"));
        List<WebElement> iList = we.findElements(By.cssSelector(".thumbnail-nav img"));
        System.out.println("iList.size() = " + iList.size());
        //List <WebElement> iWB=null;
        ArrayList<WebElement> iWB=null;
        for (int i = 1; i< iList.size();i++) {
            iWB.add(iList.get(i));
        }

        for(int i = 0; i<iWB.size();i++){
            System.out.println("WEBELEMENT " + i + " = " + iWB.get(i));
        }


        for(WebElement sWB : iList){
            System.out.println("sWB.getAttribute(\"src\") = " + sWB.getAttribute("src"));
        }

        /* System.out.println(driver.findElementById("circles"));
        List<WebElement> listOfElements = driver.findElements(By.id("circles"));
        System.out.println("listOfElements size = " + listOfElements.size());

        for(WebElement e: listOfElements){
            System.out.println("e = " + e);
        }*/
    }

    @After
    public void tearDown() throws InterruptedException {
        Thread.sleep(4000);

        System.out.println("Tearing down.");
        driver.quit();
    }

}
