package amazon;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AboutAuthorPage {

    private WebDriver driver;
    private WebDriverWait wait;
    @FindBy(id = "authorBooksCarousel")
    WebElement booksCarousel;
    @FindBy(id = "formatSelectorHeader")
    WebElement booksByAuthor;
    @FindBy(css = "[class='a-button-inner'] [data-action='a-dropdown-button']")
    WebElement sortBy;
    @FindBy(xpath = "//div[@class='a-popover-wrapper']//a[contains(text(), 'Price: Low to High')]")
    WebElement filterPrice;

    public AboutAuthorPage(WebDriver driver) {

        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
        PageFactory.initElements(driver, this);
    }

    public String authorsBooks(){

       return booksByAuthor.getText().trim();
    }

    public void clickOnPriceFilter(){

        sortBy.click();
        wait.until(ExpectedConditions.elementToBeClickable(filterPrice));
        filterPrice.click();
    }

    public void waitUntilPageLoad(){

        wait.until(ExpectedConditions.visibilityOf(booksCarousel));
    }
}
