package amazon;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;

public class AuthorBooksPage extends BasePage {

    private final String authorNameLoc = "//div[@class='a-section a-spacing-none']//div[@class='a-row a-size-base a-color-secondary']";
    @FindBy(xpath = authorNameLoc )
    List<WebElement> authorNameList;
    @FindBy(xpath = "//div[@class='a-row a-size-base a-color-secondary']//a[@class='a-size-base a-link-normal']")
    WebElement authorNameLink;

    public AuthorBooksPage(WebDriver driver) {
        super(driver);
    }

    public boolean isAllBooksAuthors(String authorName){

        boolean checkCondition = true;
        for (WebElement i : authorNameList){
            if (!i.getText().toLowerCase().contains(authorName)){
                 checkCondition = false;
                 break;
            }
        } return checkCondition;
    }

    public void clickOnAuthorName(){

        authorNameLink.click();
    }

    public void waitUntilPageLoad(){

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(authorNameLoc)));
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath(authorNameLoc), 12));
    }
}
