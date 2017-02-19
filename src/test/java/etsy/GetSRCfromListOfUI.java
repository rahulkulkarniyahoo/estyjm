package etsy;

import net.serenitybdd.core.annotations.findby.By;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rahul.kulkarni on 16/02/2017.
 */
public class GetSRCfromListOfUI {

    ChromeDriver driver;
    @Before
    public void startUp(){
        System.out.println("Started");
       // System.setProperty("webdriver.gecko.driver", "src/test/resources/geckodriver");
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");

       //  <webdriver.chrome.driver>${project.basedir}/src/test/resources/chromedriver</webdriver.chrome.driver>
        System.out.println(" ********** Setting Property - Done *********");
        //driver = new FirefoxDriver();
        driver = new ChromeDriver();
        driver.get("https://www.etsy.com/uk/listing/465419784/printed-silk-kimono-top-blur");
    }

    @Test
    public void myGetSRC() throws IOException {
        System.out.println("Test begins");
        System.out.println(driver.getTitle());

        WebElement dropDown = driver.findElement( By.xpath( "//*[@id='circles']" ) );
        List<WebElement> drop = dropDown.findElements(By.cssSelector(".thumbnail-nav img"));
        System.out.println(" Size" + drop.size());

        //to get src of 1 element
        String s = drop.get(0).getAttribute("src");
        System.out.println("string s is " +s);

        List <WebElement> iWe = drop;

        /*for(int k = 0; k<drop.size();k++){
            System.out.println("This is k = " + k +" = " + iWe.get(k).getAttribute("src"));
        }*/

        for(int k = 0; k<drop.size();k++){
            System.out.println("This is k = " + k +" = " + drop.get(k).getAttribute("src"));
        }

        int sImageNumber = 1;
        for (WebElement we: drop) {
            String src = we.getAttribute("src");
            System.out.println("src of all elements " + src);
            src = src.replaceAll("75x75", "fullxfull");
           // driver.get(src);
            URL imageURL = new URL(src);
            BufferedImage saveImage = ImageIO.read(imageURL);
            ImageIO.write(saveImage, "png", new File("src/test/images/logo-forum_full" +sImageNumber +".png"));
            sImageNumber++;
        }
    }

    @After
    public void tearDown() throws InterruptedException {
        Thread.sleep(4000);

        System.out.println("Tearing down.");
        driver.quit();
    }

}
