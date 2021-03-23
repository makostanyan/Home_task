import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class greetzTest {

    private WebDriver driver;
    private String email = "HiHello@gmail.com";
    private String password = "HiHello888";

    private void loginToPage() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.greetz.nl/auth/login");
        Thread.sleep(2000);
        WebElement loginForm = driver.findElement(By.id("loginForm"));
        loginForm.findElement(By.name("email")).sendKeys(email);
        loginForm.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.id("login-cta")).click();
        Thread.sleep(30000);
    }

    @Test
    public void favoritesTest() throws InterruptedException {

        loginToPage();
        driver.get("https://www.greetz.nl/ballonnen/denken-aan");
        Thread.sleep(30000);
        String expectedPrice = driver.findElement(By.xpath("//a[@data-id='1142795629']//span[@data-qa-ref='normal-price']")).getText();
        String expectedName = driver.findElement(By.xpath("//a[@data-id='1142795629']//div[@class='b-products-grid__item-title']")).
                getText().toLowerCase();
        driver.findElement(By.xpath("//a[@data-id='1142795629']/..//a/div[@class='b-favourite']")).click();
        driver.findElement(By.xpath("//i[@data-qa-ref= 'profile-icon']")).click();
        driver.findElement(By.xpath("//span[text() = 'Favorieten']")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//div[@class= 'center full-size']")).click();
        Thread.sleep(2000);
        String actualName = driver.findElement(By.xpath("//form[@name= 'productAmountForm']/h1")).getText().toLowerCase();
        Assert.assertEquals(actualName, expectedName);
        String actualPrice = driver.findElement(By.className("price-normal")).getText().substring(2);
        Assert.assertEquals(actualPrice, expectedPrice);
        driver.findElement(By.xpath("//div[text() =  'Toegevoegd aan favorieten']")).click();
        driver.quit();
    }

    @Test
    public void cardsTest() throws InterruptedException {

        loginToPage();
        driver.get("https://www.greetz.nl/kaarten/denken-aan");
        Thread.sleep(5000);
        driver.findElement(By.cssSelector("[data-id='3000011108']")).click();
        Thread.sleep(2000);
        WebElement element = driver.findElement(By.xpath("//div[@class='page-detail__select']//div//input"));
        element.clear();
        element.sendKeys("2");
        String perPrice = driver.findElement(By.xpath("//g-price[@class='price-card price-block']//span[@class='price-normal']")).getText();
        String doublePerPrice = String.valueOf(Double.parseDouble((perPrice.substring(2).replace(",", "."))) * 2);
        String totalPrice = driver.findElement(By.xpath("//span//div[@class='price-total']")).getText().replace(',', '.');
        Assert.assertTrue(totalPrice.contains(doublePerPrice));
        driver.quit();
    }
}
