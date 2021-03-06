import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.util.List;
import java.util.Random;

public class GreetzTest {

    private WebDriver driver;

    @BeforeMethod
    private void loginToPage() throws InterruptedException {

        String email = "HiHello@gmail.com";
        String password = "HiHello888";
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.greetz.nl/auth/login");
        Thread.sleep(2000);
        WebElement loginForm = driver.findElement(By.id("loginForm"));
        loginForm.findElement(By.name("email")).sendKeys(email);
        loginForm.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.id("login-cta")).click();
        Thread.sleep(2000);
    }

    @AfterMethod
    public void driverQuit (){

        driver.quit();
    }

    @Test(priority = 2)
    public void favoritesTest() throws InterruptedException {

        driver.get("https://www.greetz.nl/ballonnen/denken-aan");
        Thread.sleep(2000);
        WebElement productComponents = driver.findElement(By.cssSelector("div.b-products-grid__item:nth-child(1)"));
        String expectedPrice = productComponents.findElement(By.className("b-products-grid__item-price")).getText();
        String expectedName = productComponents.findElement(By.className("b-products-grid__item-title")).getText().toLowerCase();
        productComponents.findElement(By.className("b-favourite")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//i[@data-qa-ref= 'profile-icon']")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//span[text() = 'Favorieten']")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//div[@class= 'center full-size']")).click();
        Thread.sleep(1000);
        WebElement detail = driver.findElement(By.className("page-detail__sidebar-container"));
        String actualName = detail.findElement(By.className("giftdetails--title")).
                getText().toLowerCase();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualName, expectedName, "Names are different");
        String actualPrice = detail.findElement(By.className("price-normal")).getText().
                replaceAll("[^\\d,]", "");
        softAssert.assertEquals(actualPrice, expectedPrice, "Prices are different");
        softAssert.assertAll();
        driver.findElement(By.className("productdetails-favorite")).click();
        Thread.sleep(1000);
    }

    @Test(priority = 1)
    public void cardsTest() throws InterruptedException {

        driver.get("https://www.greetz.nl/kaarten/denken-aan");
        Thread.sleep(5000);
        List<WebElement> results = driver.findElements(By.xpath("//a[@data-qa-ref='product-wall-item']"));
        Random random = new Random();
        results.get(random.nextInt(results.size())).click();
        Thread.sleep(3000);
        WebElement element = driver.findElement(By.xpath("//div[@class='page-detail__select']//div//input"));
        element.clear();
        element.sendKeys("2");
        String perPrice = driver.findElement(By.xpath("//g-price[@class='price-card price-block']//span[@class='price-normal']")).getText();
        double doublePerPrice = Double.parseDouble(perPrice.replaceAll("[^\\d,]", "").replace(',', '.')) * 2;
        String totalPrice = driver.findElement(By.xpath("//span//div[@class='price-total']")).getText();
        double doubleTotalPrice = Double.parseDouble(totalPrice.replaceAll("[^\\d,]", "").replace(',', '.'));
        Assert.assertEquals(doublePerPrice, doubleTotalPrice, "Total amount is incorrect");
    }
}
