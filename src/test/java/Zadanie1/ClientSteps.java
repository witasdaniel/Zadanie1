package Zadanie1;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static java.lang.Thread.sleep;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ClientSteps {
  private static WebDriver driver;
  private Map<String, Object> vars;
  JavascriptExecutor js;
  @Given("^open website https://prod-kurs\\.coderslab\\.pl/index\\.php$")
  public void setUp() throws InterruptedException{
    System.setProperty("webdriver.chrome.driver","src/main/resources/drivers/chromedriver.exe");
    driver = new ChromeDriver();
    driver.get("https://prod-kurs.coderslab.pl/index.php");
      sleep(2000);
  }
  @When("^he chooses to log in$")
  public void heChoosesToLogin() throws InterruptedException {
    driver.findElement(By.cssSelector(".user-info > a")).click();
    Thread.sleep(1000);
  }
  @And("^he provides the \"([^\"]*)\",\"([^\"]*)\"$")
  public void heProvidesThe(String login, String password) throws Throwable {
    driver.findElement(By.name("email")).sendKeys(login);
    driver.findElement(By.name("password")).sendKeys(password);
  }

  @And("^he clicks login button$")
  public void heClicksLoginButton() {
    driver.findElement(By.id("submit-login")).click();
  }

  @Then("^he should be logged in$")
  public void heShouldBeLoggedIn() {
      assertThat(driver.findElement(By.cssSelector(".logout")).getText(), is(" Sign out"));
  }

  @Then("^he chooses to add address$")
  public void heChoosesToAddAddress() {
   //   driver.findElement(By.cssSelector("#addresses-link .material-icons")).click();
   //   driver.findElement(By.cssSelector(".addresses-footer span")).click();
      driver.get("https://prod-kurs.coderslab.pl/index.php?controller=address");
  }

  @And("^he provides the \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", country, \"([^\"]*)\" and save them$")
  public void heProvidesTheAliasAddressCityZipPostalCodeCountryPhoneAndSaveThem(String alias, String company, String address, String phone, String postcode) {
      driver.findElement(By.name("alias")).sendKeys(alias);
      driver.findElement(By.name("company")).sendKeys(company);
      driver.findElement(By.name("vat_number")).sendKeys("9222222222");
      driver.findElement(By.name("address1")).sendKeys(address);
      driver.findElement(By.name("city")).sendKeys("Warszawa");
      driver.findElement(By.name("postcode")).sendKeys("50-555");
      driver.findElement(By.name("id_country")).click();
      {
          WebElement dropdown = driver.findElement(By.name("id_country"));
          dropdown.findElement(By.xpath("//option[. = 'United Kingdom']")).click();
      }
      driver.findElement(By.name("id_country")).click();
      driver.findElement(By.name("phone")).click();
      driver.findElement(By.name("phone")).sendKeys(phone);
      driver.findElement(By.cssSelector(".form-footer > .btn")).click();
  }

  @Then("^he should be redirected back with new address saved on his account$")
  public void heShouldBeRedirectedBackWithNewAddressSavedOnHisAccount() {
    String successmsg = driver.findElement(By.xpath("//*[@id=\"notifications\"]/div/article/ul/li")).getText();
    Assert.assertEquals(successmsg,"Address successfully added!");
    driver. close();
  }

 //   @And
 //   public void heProvidesTheCountryAndSaveThem(String arg0, String arg1, String arg2, String arg3, String arg4) throws Throwable {
 //       // Write code here that turns the phrase above into concrete actions
 //      throw new PendingException();
    }

