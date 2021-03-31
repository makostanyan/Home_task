package amazon;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

    private WebDriver driver;
    private By deliverLoc = By.id("glow-ingress-block");
    private By searchDropdownLoc = By.id("nav-search-dropdown-card");
    private By booksLoc = By.cssSelector("[value='search-alias=stripbooks-intl-ship']");
    private By search = By.id("twotabsearchtextbox");
    private By homePageLoad = By.id("navbar");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public String deliveryToArmenia(){

        return driver.findElement(deliverLoc).getText().replaceAll("\\s", "");
    }

    public void bookSearch(){

        driver.findElement(searchDropdownLoc).click();
        driver.findElement(booksLoc).click();
    }

    public void authorSearch(String authorName){

        driver.findElement(search).sendKeys(authorName, Keys.ENTER);
    }


    public void waitUntilPageLoad(){

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(homePageLoad));
    }
}
