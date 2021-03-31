package amazon;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;

public class AuthorBooksPage {

    private WebDriver driver;
    private By authorNameLoc = By.xpath("//div[@class='a-section a-spacing-none']//div[@class='a-row a-size-base a-color-secondary']");
    private By authorNameLinkLoc = By.xpath("//div[@class='a-row a-size-base a-color-secondary']//a[@class='a-size-base a-link-normal']");

    public AuthorBooksPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isAllBooksAuthors(String authorName){

        List<WebElement> booksList = driver.findElements(authorNameLoc);
        boolean checkCondition = true;
        for (WebElement i : booksList){
            if (!i.getText().toLowerCase().contains(authorName)){
                 checkCondition = false;
                 break;
            }
        } return checkCondition;
    }

    public void clickOnAuthorName(){

        driver.findElement(authorNameLinkLoc).click();
    }

    public void waitUntilPageLoad(){

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(authorNameLoc));
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(authorNameLoc, 12));
    }
}