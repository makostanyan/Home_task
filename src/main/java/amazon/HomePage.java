package amazon;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

    private WebDriver driver;
    private WebDriverWait wait;
    @FindBy(id = "glow-ingress-block")
    WebElement deliverTo;
    @FindBy(id = "nav-search-dropdown-card")
    WebElement searchDropdown;
    @FindBy(css = "[value='search-alias=stripbooks-intl-ship']")
    WebElement booksDepartment;
    @FindBy(id = "twotabsearchtextbox")
    WebElement search;
    @FindBy(id = "navbar")
    WebElement homePageLoad;

    public HomePage(WebDriver driver) {

        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
        PageFactory.initElements(driver, this);
    }

    public String deliveryToArmenia(){

        return deliverTo.getText().replaceAll("\\s", "");
    }

    public void clickOnBookDepartment(){

        searchDropdown.click();
        wait.until(ExpectedConditions.elementToBeClickable(booksDepartment));
        booksDepartment.click();
    }

    public void authorSearch(String authorName){

        search.sendKeys(authorName, Keys.ENTER);
    }

    public void waitUntilPageLoad(){

        wait.until(ExpectedConditions.visibilityOf(homePageLoad));
    }
}
