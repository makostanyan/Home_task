package amazon;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AboutAuthorPage {

    private WebDriver driver;
    private By booksCarouselLoc = By.id("authorBooksCarousel");
    private By booksByAuthor = By.id("formatSelectorHeader");
    private By sortBy = By.cssSelector("[class='a-button-inner'] [data-action='a-dropdown-button']");
    private By filterPrice = By.xpath("//div[@class='a-popover-wrapper']//a[contains(text(), 'Price: Low to High')]");

    public AboutAuthorPage(WebDriver driver) {

        this.driver = driver;
    }

    public String authorsBooks(){

       return driver.findElement(booksByAuthor).getText().trim();
    }

    public void filterSortBy(){

        driver.findElement(sortBy).click();
    }

    public void clickOnPriceFilter(){

        driver.findElement(filterPrice).click();
    }

    public void waitUntilPageLoad(){

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(booksCarouselLoc));
    }
}
