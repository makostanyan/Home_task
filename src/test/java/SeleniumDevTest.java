import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;

public class SeleniumDevTest {

    @Test
    public void seleniumVersionTest() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.selenium.dev/");
        driver.manage().window().maximize();
        Thread.sleep(3000);
        WebElement downloads = driver.findElement(By.xpath("//nav/a[contains(text(),'Downloads')]"));
        downloads.click();
        WebElement latestVersion = driver.findElement(By.xpath("//p[contains(text(), 'Latest stable version ')]//a"));
        String actualResult = latestVersion.getText();
        String expectedResult = "3.141.59";
        Assert.assertEquals(actualResult, expectedResult);

        WebElement search = driver.findElement(By.name("search"));
        search.sendKeys("selenium webdriver" + Keys.ENTER);
        Thread.sleep(2000);
        List<WebElement> results = driver.findElements(By.xpath("//div[@class = 'gs-title']//a"));

        boolean checkCondition = false;
        for (WebElement i : results) {
            if (i.getText().toLowerCase().contains("selenium webdriver")) {
                checkCondition = true;
                break;
            }
        }
        Assert.assertTrue(checkCondition, "Pass");
        driver.quit();
    }
}




