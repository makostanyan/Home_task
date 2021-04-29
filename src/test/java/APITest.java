import gorest.GorestRequest;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class APITest {

    GorestRequest reqMethod = new GorestRequest();
    SoftAssert softAssert = new SoftAssert();
    String expectedName = "Hi Hello8";
    String expectedEmail = "HiHello8@test.com";
    String expectedGender = "Male";
    String expectedStatus = "Active";
    String expectedMessage = "Resource not found";

    @BeforeMethod
    public void setPath() {

        RestAssured.baseURI = "https://gorest.co.in/";
        RestAssured.basePath = "public-api/";
    }

    @Test
    public void postUserTest() {

        ValidatableResponse postResponse =  reqMethod.postUser();
        String id = reqMethod.getId(postResponse);
        ValidatableResponse getResponse = reqMethod.getUser(id);
        String actualName = reqMethod.getName(getResponse);
        String actualEmail = reqMethod.getEmail(getResponse);
        String actualGender = reqMethod.getGender(getResponse);
        String actualStatus = reqMethod.getStatus(getResponse);
        softAssert.assertEquals(actualName, expectedName,"Request and response names are different");
        softAssert.assertEquals(actualEmail, expectedEmail,"Request and response emails are different");
        softAssert.assertEquals(actualGender, expectedGender, "Request and response genders are different");
        softAssert.assertEquals(actualStatus, expectedStatus, "Request and response statuses are different");
        softAssert.assertAll();
        reqMethod.deleteUser(id);
    }

    @Test
    public void deleteUserTest(){

        ValidatableResponse postResponse =  reqMethod.postUser();
        String id = reqMethod.getId(postResponse);
        reqMethod.deleteUser(id);
        ValidatableResponse getResponse = reqMethod.getUser(id);
        String actualMessage = reqMethod.getMessage(getResponse);
        softAssert.assertEquals(actualMessage, expectedMessage, "User in not deleted");
        softAssert.assertAll();
    }
}
