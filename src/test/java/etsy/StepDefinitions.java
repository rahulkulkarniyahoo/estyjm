package etsy;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import pages.UserMainPage;

/**
 * Created by rahul.kulkarni on 14/02/2017.
 */
public class StepDefinitions {
    UserMainPage userMainPage;

    @Before
    public void startUp(){

        System.setProperty("webdriver.gecko.driver", "src/test/resources/geckodriver");
        System.out.println(" ********** Setting Property - Done *********");
    }

    @Given("^User opens etsy user website$")
    public void user_opens_etsy_user_website() throws Throwable {
        System.out.println("Given");
        userMainPage.open();

    }

    @When("^User clicks on All Items$")
    public void user_clicks_on_All_Items() throws Throwable {
        System.out.println("When");
        userMainPage.clickViewAllItems();

    }

    @Then("^User can see All Items on the page$")
    public void user_can_see_All_Items_on_the_page() throws Throwable {
        System.out.println("Then");
        Thread.sleep(5000);
        userMainPage.listAllItems();

    }

}
