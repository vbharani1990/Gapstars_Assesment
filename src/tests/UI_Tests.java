package tests;

import pages.Home_Page;
import pages.AB_Testing_Page;
import pages.Add_Element_Page;
import pages.DropdownPage;
import utils.Utilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

@TestInstance(value = TestInstance.Lifecycle.PER_CLASS)
public class UI_Tests extends Utilities {


    Home_Page home_Page = new Home_Page();
    AB_Testing_Page ab_Testing_Page = new AB_Testing_Page();
    Add_Element_Page add_Element_Page = new Add_Element_Page();
    DropdownPage dropdwon_Page = new DropdownPage();

    By Basic_Auth_Header = By.xpath("//*[@id=\"content\"]/div/p");
    By Checkbox1 = By.xpath("//*[@id=\"checkboxes\"]/input[1]");

    @org.junit.jupiter.api.Test
    public void ABTesting() {
        clickElement(home_Page.AB_Testing_Link);
        //Wait for the expected element to be visible/loaded
        wait.until(ExpectedConditions.visibilityOfElementLocated(ab_Testing_Page.AB_Testing_Title));
        isElementExist(ab_Testing_Page.AB_Testing_Title);
    }

    @org.junit.jupiter.api.Test
    public void AddRemoveElements() {
        //Wait for the expected element to be visible/loaded
        wait.until(ExpectedConditions.visibilityOfElementLocated(home_Page.Add_Remove_Link));
        //Clicking the Add/Remove Elements Link
        clickElement(home_Page.Add_Remove_Link);
        //Wait for the expected element to be visible/loaded
        wait.until(ExpectedConditions.visibilityOfElementLocated(add_Element_Page.Add_Element_Button));
        clickElement(add_Element_Page.Add_Element_Button);
        WebElement elementsDom = driver.findElement(add_Element_Page.elements_dom);
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(add_Element_Page.Delete_Button));
        isElementExist(add_Element_Page.Delete_Button);
    }

    @org.junit.jupiter.api.Test
    public void Checkboxes() {
        //Wait for the expected element to be visible/loaded
        wait.until(ExpectedConditions.visibilityOfElementLocated(home_Page.Checkboxes_link));
        //Clicking the Checkboxes Link
        clickElement(home_Page.Checkboxes_link);
        //Wait for the expected element to be visible/loaded
        wait.until(ExpectedConditions.visibilityOfElementLocated(Checkbox1));
        clickElement(Checkbox1);
    }

    @org.junit.jupiter.api.Test
    public void BasicAuth() {
        String username = "admin";
        String password = "admin";

        // Adding username, password with URL
        String URL = "https://" +username +":" +password +"@"+ "the-internet.herokuapp.com/basic_auth";
        driver.get(URL);

        //Wait for the expected element to be visible/loaded
        wait.until(ExpectedConditions.visibilityOfElementLocated(Basic_Auth_Header));
        isElementExist(Basic_Auth_Header);
    }

    @org.junit.jupiter.api.Test
    public void DropDownList() {
        //Wait for the expected element to be visible/loaded
        wait.until(ExpectedConditions.visibilityOfElementLocated(home_Page.Dropdown_link));
        //Clicking the Dropdown Link
        clickElement(home_Page.Dropdown_link);
        //Wait for the expected element to be visible/loaded
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dropdown")));
        selectFromDropdown(dropdwon_Page.dropdown,"Option 1");

        Select dropdown = new Select(driver.findElement(By.id("dropdown")));
        //Verifying whether the correct element is selected or not
        WebElement selectedElement = dropdown.getFirstSelectedOption();
        Assertions.assertEquals("Option 1", selectedElement.getText());
    }
}