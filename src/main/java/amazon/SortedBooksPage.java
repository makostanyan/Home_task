package amazon;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.ArrayList;
import java.util.List;

public class SortedBooksPage {

    private WebDriver driver;
    private By pageLodLoc = By.className("a-fixed-left-grid-col");
    private By priceLoc = By.className("a-size-base-plus a-color-price a-text-bold");

    public SortedBooksPage(WebDriver driver) {

        this.driver = driver;
    }

    private ArrayList<Double> elementListToArrayList(){

        List<WebElement> price = driver.findElements(priceLoc);
        ArrayList<Double>  priceList = new ArrayList<>();
        for (WebElement i : price){
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

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(pageLodLoc));
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(pageLodLoc, 20));
    }
}
