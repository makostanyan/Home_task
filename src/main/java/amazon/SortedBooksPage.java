package amazon;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.ArrayList;
import java.util.List;

public class SortedBooksPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private final String pageLodLoc = "a-fixed-left-grid-col";
    @FindBy(className = pageLodLoc)
    WebElement pageLod;
    @FindBy(className = "a-size-base-plus a-color-price a-text-bold")
    List<WebElement> elementPriceList;

    public SortedBooksPage(WebDriver driver) {

        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
        PageFactory.initElements(driver, this);
    }

    private ArrayList<Double> elementListToArrayList(){

        ArrayList<Double>  priceList = new ArrayList<>();
        for (WebElement i : elementPriceList){
            priceList.add(Double.parseDouble(i.getText().replaceAll("[^\\d.]", "")));}
        return priceList;
    }

    public boolean isSortedBooksList() {

        ArrayList<Double> priceList = elementListToArrayList();
        for (int i = 0; i < priceList.size() - 1; i++){
            if (priceList.get(i) >= priceList.get(i + 1)) {
                return false;
            }
        } return true;
    }

    public void waitUntilPageLoad(){

        wait.until(ExpectedConditions.visibilityOf(pageLod));
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.className(pageLodLoc), 20));
    }
}
