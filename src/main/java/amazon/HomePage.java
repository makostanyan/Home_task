package amazon;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

    private WebDriver driver;

    @FindBy(id = "glow-ingress-block")
    WebElement deliverLoc;
    @FindBy(id = "nav-search-dropdown-card")
    WebElement searchDropdownLoc;
    @FindBy(css = "[value='search-alias=stripbooks-intl-ship']")
    WebElement booksLoc;
    @FindBy(id = "twotabsearchtextbox")
    WebElement search;
    private By homePageLoad = By.id("navbar");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String deliveryToArmenia(){

        return deliverLoc.getText().replaceAll("\\s", "");
    }

    public void clickOnBookDepartment(){

        searchDropdownLoc.click();
        booksLoc.click();
    }

    public void authorSearch(String authorName){

        search.sendKeys(authorName, Keys.ENTER);
    }


    public void waitUntilPageLoad(){

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(homePageLoad));
    }
}
