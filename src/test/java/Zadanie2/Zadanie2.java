package Zadanie2;


import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.Map;
import java.util.regex.Pattern;
import org.apache.commons.io.FileUtils;
import org.junit.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import static java.lang.Thread.sleep;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.junit.Assert.assertThat;
import static org.openqa.selenium.Keys.*;

public class Zadanie2 {
    private static WebDriver driver;
    private Map<String, Object> vars;
    JavascriptExecutor js;
    @Given("^the user is on landing page$")
    public void theUserIsOnLandingPage() {
        System.setProperty("webdriver.chrome.driver","src/main/resources/drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://prod-kurs.coderslab.pl/index.php");
    }
    @When("^he wants to log in$")
    public void heWantsToLogIn() throws InterruptedException {
            driver.findElement(By.cssSelector(".user-info > a")).click();
            Thread.sleep(1000);
    }
    @And("^he enter the \"([^\"]*)\",\"([^\"]*)\"$")
    public void heEnterThe(String login, String password) throws Throwable {
        driver.findElement(By.name("email")).sendKeys(login);
        driver.findElement(By.name("password")).sendKeys(password);
        //throw new PendingException();
    }
    @And("^he confirms login$")
    public void heConfirmsLogin() {
        driver.findElement(By.cssSelector(".form-group:nth-child(2)")).click();
        driver.findElement(By.cssSelector(".form-group:nth-child(3)")).click();
        driver.findElement(By.id("submit-login")).click();
    }
    @Then("^he should be logged in correctly$")
    public void heShouldBeLoggedInCorrectly() {
        assertThat(driver.findElement(By.cssSelector(".logout")).getText(), is(" Sign out"));
    }

    @Then("^he navigates trough clothes into Hummingbird Printed Sweater$")
    public void heNavigatesTroughClothesIntoHummingbirdPrintedSweater() {
        driver.findElement(By.cssSelector("#category-3 > .dropdown-item")).click();
        driver.findElement(By.cssSelector(".product-miniature:nth-child(2) img")).click();
        driver.findElement(By.id("group_1")).click();
    }

    @And("^he adds \"([^\"]*)\" pieces of \"([^\"]*)\" size to cart$")
    public void heAddThemToCart(String size, String quantity) throws Throwable {
        new Select(driver.findElement(By.id("group_1"))).selectByVisibleText("M");
        WebElement webElement = driver.findElement(By.xpath("//*[@class='material-icons touchspin-up']"));
        for(int i=0; i<5; i++){
            webElement.click();
            Thread.sleep(2000);}
        driver.findElement(By.xpath("(//button[@type='submit'])[2]")).click();
    }

    @Then("^he goes to checkout$")
    public void heGoesToCheckout() throws Throwable {
        //driver.switchTo().activeElement();
        sleep(1000);
        driver.findElement(By.xpath("//div[@class='cart-content-btn']/child::a")).click();
        sleep(1000);
        driver.findElement(By.xpath("//*[@class='btn btn-primary']")).click();
    }

    @Then("^he confirms address$")
    public void heConfirmsAddress() {
       /* {
            WebElement element = driver.findElement(By.cssSelector(".add-to-cart"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element).perform();
        }
        {
            WebElement element = driver.findElement(By.tagName("body"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element, 0, 0).perform();
        }*/
        driver.findElement(By.name("confirm-addresses")).click();
    }

//    @Given("^the address is filled correctly$")
//       public void theAddressIsFilledCorrectly() {
//       assertTrue(driver.findElement(By.cssSelector("#id-address-delivery-address-1672 .address")).getText().matches("(Adam Oto\\\\nOttona 6\\\\n9222222222\\\\nLondyn\\\\nWarszawa\\\\n50-555\\\\nUnited Kingdom\\\\n4|Daniel Iks\nCompany\n9222222222\nOkęcie 5\nWarszawa\n50-555\nUnited Kingdom\n+48510531478+48510521478"));
//    }

    @Then("^he gets into shipping method$")
    public void heGetsIntoShippingMethod() {
        driver.findElement(By.name("confirmDeliveryOption")).click();
    }

    @And("^he chooses pick up in store$")
    public void heChoosesPickUpInStore() {
        driver.findElement(By.cssSelector("#checkout-delivery-step .step-edit")).click();
    }

    @And("^he confirms$")
    public void heConfirms() {
        driver.findElement(By.name("confirmDeliveryOption")).click();
    }

    @And("^he chooses to pay by check$")
    public void heChoosesToPayByCheck() {
        driver.findElement(By.id("payment-option-1")).click();
    }

 /*   @And("^he confirms payment$")
    public void heConfirmsPayment() {
    }
*/
    @Given("^he accepts to terms of service$")
    public void heAcceptsToTermsOfService() {
        driver.findElement(By.id("conditions_to_approve[terms-and-conditions]")).click();
    }

    @And("^he confirms order$")
    public void heConfirmsOrder() throws Throwable {
        driver.findElement(By.cssSelector(".ps-shown-by-js > .btn")).click();
        assertThat(driver.getTitle(), is("Order confirmation"));
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File("order.png"));
        sleep(2000);
    }

    @Then("^he logs out$")
    public void heLogsOut() {
        driver.findElement(By.cssSelector(".logout")).click();
        driver.quit();
    }

}
