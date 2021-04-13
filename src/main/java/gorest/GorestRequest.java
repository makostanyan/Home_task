package gorest;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;


public class GorestRequest {

    String body = """
            {
            "name": "Hi Hello8",
            "email": "HiHello8@test.com",
            "gender": "Male",
            "status": "Active"
            }
            """;


    public ValidatableResponse postUser() {

        ValidatableResponse response = RestAssured
                .given()
                .spec(requestSpecification())
                .body(body)
                .when()
                .post("users")
                .then();
        return response;
    }


    public String getId(ValidatableResponse response) {

        int id = response.extract().response().jsonPath().getInt("data.id");
        return String.valueOf(id);
    }

    public String getName(ValidatableResponse response) {

        String name = response.extract().response().jsonPath().getString("data.name");
        return name;
    }

    public String getEmail(ValidatableResponse response) {

        String email = response.extract().response().jsonPath().getString("data.email");
        return email;
    }

    public String getGender(ValidatableResponse response) {

        String gender = response.extract().response().jsonPath().getString("data.gender");
        return gender;
    }

    public String getStatus(ValidatableResponse response) {

        String status = response.extract().response().jsonPath().getString("data.status");
        return status;
    }

    public String getMessage(ValidatableResponse response) {

        String message = response.extract().response().jsonPath().getString("data.message");
        return message;
    }


    public ValidatableResponse getUser(String id) {

        ValidatableResponse response = RestAssured
                .given()
                .when()
                .get("users/" + id)
                .then()
                .spec(responseSpecification());
        response.extract().response().prettyPrint();
        return response;
    }

    public void deleteUser(String id) {

        RestAssured
                .given()
                .spec(requestSpecification())
                .when()
                .delete("users/" + id)
                .then();
    }

    private RequestSpecification requestSpecification() {

        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        return requestSpecBuilder
                .setContentType(ContentType.JSON)
                .addHeader("Authorization", "Bearer 9535422919a34dc5596f6c027c35c1ccdfd0ec1d790751e4e031a5e58ae61577")
                .build();
    }

    private ResponseSpecification responseSpecification() {

        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder();
        return responseSpecBuilder
                .expectContentType(ContentType.JSON)
                .expectStatusCode(200)
                .build();
    }
}
