package utils;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Utilities{
    public WebDriver driver;
    public WebDriverWait wait;

    @org.junit.jupiter.api.BeforeAll
    public void setUp() {
        //Assigning the Chromedriver as the Webdriver
        System.setProperty("webdriver.chrome.driver", ".\\chromedriver.exe");
        driver = new ChromeDriver();
        //Navigating to the Homepage
        driver.get("https://the-internet.herokuapp.com/");
        //Maximizing the Window
        driver.manage().window().maximize();
        //Initializing the wait method for wait time of 30 seconds
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    //Below Method will Close the webdriver after every test.
    @org.junit.jupiter.api.AfterEach
    public void cleanup(){
        //Closing the webdriver
        driver.quit();
    }
    public void clickElement(By locator)
    {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        driver.findElement(locator).click();
    }

    public void isElementExist(By locator)
    {
        boolean isElementExist = driver.findElement((locator)).isDisplayed();
        Assertions.assertTrue(isElementExist);
    }

    public void selectFromDropdown(By locator, String dropdownValue)
    {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        Select dropdown = new Select(driver.findElement(locator));
        dropdown.selectByVisibleText(dropdownValue);
    }
}
