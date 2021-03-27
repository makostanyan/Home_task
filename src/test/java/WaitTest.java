import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;
import java.util.Random;

public class WaitTest {

    private WebDriver driver;

    @Test
    public void shoppingBagTest() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.6pm.com/");
        driver.manage().window().maximize();
        Actions actions = new Actions(driver);
        WebElement accessories = driver.findElement(By.xpath("//a[@href='/c/accessories']"));
        actions.moveToElement(accessories).perform();
        WebElement aviators = driver.findElement(By.xpath("//a[text() = 'Aviators']"));
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(aviators));
        aviators.click();
        By loc = By.xpath("//div[@id='searchPage']//div//article");
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(loc, 99));
        List<WebElement> results = driver.findElements(loc);
        Random random = new Random();
        results.get(random.nextInt(results.size())).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div//button[@type='submit' and text()='Add to Shopping Bag']")));
        String expectedPrice = driver.findElement(By.xpath("//div[@class='Ib-z']//span[@class='tK-z yK-z']")).getAttribute("aria-label");
        String expectedName = driver.findElement(By.xpath("//span[@class='eN-z']")).getText().toLowerCase();
        driver.findElement(By.xpath("//div//button[@type='submit' and text()='Add to Shopping Bag']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("ph-z")));
        WebElement bagProductComponents = driver.findElement(By.className("ph-z"));
        String actualPrice = bagProductComponents.findElement(By.className("yh-z")).getText();
        String actualName = bagProductComponents.findElement(By.className("Xk-z")).getText().toLowerCase();
        Assert.assertEquals(actualName,expectedName);
        Assert.assertEquals(actualPrice, expectedPrice);
        driver.findElement(By.name("quantity")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select//option[text()='Remove']")));
        driver.findElement(By.xpath("//select//option[text()='Remove']")).click();
        driver.quit();
    }
}
