package amazon;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AboutAuthorPage extends BasePage {

    @FindBy(id = "authorBooksCarousel")
    WebElement booksCarousel;
    @FindBy(id = "formatSelectorHeader")
    WebElement booksByAuthor;
    @FindBy(css = "[class='a-button-inner'] [data-action='a-dropdown-button']")
    WebElement sortBy;
    @FindBy(xpath = "//div[@class='a-popover-wrapper']//a[contains(text(), 'Price: Low to High')]")
    WebElement filterPrice;

    public AboutAuthorPage(WebDriver driver) {
        super(driver);
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
