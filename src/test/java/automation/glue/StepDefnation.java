package automation.glue;


import Automation.configuration.AutomationFrameworkConfiguration;
import Automation.driver.DriverSingleton;
import Automation.pages.CheckoutPage;
import Automation.pages.HomePage;
import Automation.pages.SignInPage;
import Automation.utils.*;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@ContextConfiguration(classes = AutomationFrameworkConfiguration.class)
public class StepDefnation {
    private WebDriver driver;
    private HomePage homePage;
    private CheckoutPage checkoutPage;
    private SignInPage signInPage;
    ExtentTest test;
    static ExtentReports reports =  new ExtentReports("report/TestReport.html");


    @Autowired
    ConfigurationProperties configurationProperties;

    @Before
    public void initializeObjects() {
//        System.out.println("hello");
        DriverSingleton.getInstance(configurationProperties.getBrowser());
        homePage = new HomePage();
        signInPage = new SignInPage();
        checkoutPage = new CheckoutPage();
        TestCases[] testCases= TestCases.values();
        test = reports.startTest(testCases[Utils.testCount].getTestName());
        Log.getLogData(Log.class.getName());
        Log.startTest(testCases[Utils.testCount].getTestName());
        Utils.testCount++;
    }


    @Given("^I go to the Website")
    public void i_go_to_the_website() {
        driver = new ChromeDriver();
        driver.get(Constants.URL);
        Log.info("Navigating to"+ Constants.URL);
        test.log(LogStatus.PASS, "Navigate to "+Constants.URL);
    }
    @When("^I click on Signin button")
    public void i_click_on_sign_in_button(){

        homePage.clickSignIn();
        test.log(LogStatus.PASS, "Signin button has been clicked");
    }

    @When("^I add two elements to the cart")
    public void i_add_two_elements_to_the_cart() {
        homePage.addFirstElementToCart();
        homePage.addSecondElementToCart();
        test.log(LogStatus.PASS, "Two items were added to the cart");
    }

    @And("^I specify my credential and click login")
    public void i_specify_my_credentials_and_click_login(){
        signInPage.logIn(configurationProperties.getEmail(), configurationProperties.getPassword());
        test.log(LogStatus.PASS, "Login had been clicked");
    }

    @And("^I proceed to checkout")
    public void i_proceed_to_checkout(){
        checkoutPage.goToCheckout();
        test.log(LogStatus.PASS, "We proceed the checkout");
    }

    @And("^I confirm address, shipping, payment and final order")
    public void i_confirm_address_shipping_payment_and_final_order(){
        checkoutPage.confirmAddress();
        checkoutPage.confirmShipping();
        checkoutPage.payByBankWire();
        checkoutPage.confirmFinalOrder();
        test.log(LogStatus.PASS, "We confirm the final order");
    }

    @Then("^I can Login into the system")
    public void i_can_log_into_the_website(){
        if(configurationProperties.getUsername().equals(homePage.getUserName()))
            test.log(LogStatus.PASS, "The authentication is successful.");
        else
            test.log(LogStatus.FAIL, "The authentication is not  successful.");
        assertEquals(configurationProperties.getUsername(), homePage.getUserName());
    }

    @Then("^The items are bought")
    public void the_elements_are_bought(){

        if(checkoutPage.checkFinalStatus())
            test.log(LogStatus.PASS, "The two items are bought.");
        else
            test.log(LogStatus.FAIL, "The items weren't bought.");
        assertTrue(checkoutPage.checkFinalStatus());
    }

    @After
    public void closeOject(){
        reports.endTest(test);
        reports.flush(); //to save the file to the disk
        DriverSingleton.closeObjectInstance();

    }

}


