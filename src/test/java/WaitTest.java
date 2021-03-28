import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.List;
import java.util.Random;

public class WaitTest {

    private WebDriver driver;

    @BeforeMethod
    public void loginToPage(){

        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.6pm.com/");
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void quitDriver(){

        driver.quit();
    }

    public void waitUntil(By loc){

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(loc));
    }

    public void waitUntilNumberOfElements(By loc, int number){

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(loc, number));
    }

    @Test
    public void shoppingBagTest() {

        Actions actions = new Actions(driver);
        WebElement accessories = driver.findElement(By.xpath("//a[@href='/c/accessories']"));
        actions.moveToElement(accessories).perform();
        waitUntil(By.xpath("//a[text() = 'Aviators']"));
        driver.findElement(By.xpath("//a[text() = 'Aviators']")).click();
        By loc = By.xpath("//div[@id='searchPage']//div//article");
        waitUntilNumberOfElements(loc,99);
        List<WebElement> results = driver.findElements(loc);
        Random random = new Random();
        results.get(random.nextInt(results.size())).click();
        waitUntil(By.xpath("//div//button[@type='submit' and text()='Add to Shopping Bag']"));
        String expectedPrice = driver.findElement(By.xpath("//div[@class='Ib-z']//span[@class='tK-z yK-z']")).getAttribute("aria-label");
        String expectedName = driver.findElement(By.xpath("//span[@class='eN-z']")).getText().toLowerCase();
        driver.findElement(By.xpath("//div//button[@type='submit' and text()='Add to Shopping Bag']")).click();
        waitUntil(By.className("ph-z"));
        WebElement bagProductComponents = driver.findElement(By.className("ph-z"));
        String actualPrice = bagProductComponents.findElement(By.className("yh-z")).getText();
        String actualName = bagProductComponents.findElement(By.className("Xk-z")).getText().toLowerCase();
        Assert.assertEquals(actualName,expectedName);
        Assert.assertEquals(actualPrice, expectedPrice);
        driver.findElement(By.name("quantity")).click();
        waitUntil(By.xpath("//select//option[text()='Remove']"));
        driver.findElement(By.xpath("//select//option[text()='Remove']")).click();
    }
}
