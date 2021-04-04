package amazon;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AboutAuthorPage {

    private WebDriver driver;
    private By booksCarouselLoc = By.id("authorBooksCarousel");
    @FindBy(id = "formatSelectorHeader")
    WebElement booksByAuthor;
    @FindBy(css = "[class='a-button-inner'] [data-action='a-dropdown-button']")
    WebElement sortBy;
    @FindBy(xpath = "//div[@class='a-popover-wrapper']//a[contains(text(), 'Price: Low to High')]")
    WebElement filterPrice;

    public AboutAuthorPage(WebDriver driver) {

        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String authorsBooks(){

       return booksByAuthor.getText().trim();
    }

    public void clickOnPriceFilter(){

        sortBy.click();
        filterPrice.click();
    }

    public void waitUntilPageLoad(){

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(booksCarouselLoc));
    }
}
