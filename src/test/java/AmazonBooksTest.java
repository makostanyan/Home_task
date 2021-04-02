import amazon.AboutAuthorPage;
import amazon.AuthorBooksPage;
import amazon.HomePage;
import amazon.SortedBooksPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class AmazonBooksTest {

    private WebDriver driver;
    String authorName = "Agatha Christie";

    @BeforeMethod
    public void openPage() {

        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.amazon.com/");
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void quitDriver(){

        driver.quit();
    }

    @Test
    public void booksTest(){

        HomePage homePage = new HomePage(driver);
        homePage.waitUntilPageLoad();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(homePage.deliveryToArmenia(), "DelivertoArmenia",
                "It is not delivery to Armenia");
        homePage.clickOnBookDepartment();
        homePage.waitUntilPageLoad();
        homePage.authorSearch(authorName);
        AuthorBooksPage authorBooksPage = new AuthorBooksPage(driver);
        authorBooksPage.waitUntilPageLoad();
        softAssert.assertTrue(authorBooksPage.isAllBooksAuthors(authorName.toLowerCase()),
                "Book was written by another author");
        authorBooksPage.clickOnAuthorName();
        AboutAuthorPage aboutAuthorPage = new AboutAuthorPage(driver);
        aboutAuthorPage.waitUntilPageLoad();
        softAssert.assertEquals(aboutAuthorPage.authorsBooks(),"Books By " + authorName,
                "Missing statement Books By {Author name} ");
        aboutAuthorPage.clickOnPriceFilter();
        SortedBooksPage sortedBooksPage = new SortedBooksPage(driver);
        sortedBooksPage.waitUntilPageLoad();
        softAssert.assertTrue(sortedBooksPage.isSortedBooksList(), "Books list is not sorted");
        softAssert.assertAll();
    }
}
