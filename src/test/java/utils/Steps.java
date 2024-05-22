package utils;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.testng.AssertJUnit.assertTrue;

public class Steps {
    @Step
    public static Response GET(String endpoint)
    {

        Allure.addAttachment("URL", baseURI + endpoint);
        Response response = get(endpoint);
        Allure.addAttachment("Response body", response.body().prettyPrint());
        Allure.addAttachment("Status code", String.valueOf(response.statusCode()));
        return response;
    }
    @Step
    public static void isstatuscodevalid(Response response, int expectedStatuscode){
        response.then().assertThat().statusCode(expectedStatuscode);
    }
    @Step
    public static Response POST(String body, String endpoint, String contenttype)
    {
        Allure.addAttachment("URL", baseURI + endpoint);
        Allure.addAttachment("Body", body);
        Response response = given().contentType(contenttype).body(body).post(endpoint);
        Allure.addAttachment("Response body", response.body().prettyPrint());
        Allure.addAttachment("Status code", String.valueOf(response.statusCode()));
        return response;
    }

    @Step
    public static Response PATCH(String body, String endpoint, String contenttype)
    {
        Allure.addAttachment("URL", baseURI + endpoint);
        Allure.addAttachment("Body", body);
        Response response = given().contentType(contenttype).body(body).post(endpoint);
        Allure.addAttachment("Response body", response.body().prettyPrint());
        Allure.addAttachment("Status code", String.valueOf(response.statusCode()));
        return response;
    }
    @Step
    public static Response PUT(String body, String endpoint, String contenttype)
    {
        Allure.addAttachment("URL", baseURI + endpoint);
        Allure.addAttachment("Body", body);
        Response response = given().contentType(contenttype).body(body).post(endpoint);
        Allure.addAttachment("Response body", response.body().prettyPrint());
        Allure.addAttachment("Status code", String.valueOf(response.statusCode()));
        return response;
    }
    @Step
    public static Response DELETE(String body, String endpoint)
    {
        Allure.addAttachment("URL", baseURI + endpoint);
        Allure.addAttachment("Body", body);
        Response response = given().body(body).delete(endpoint);
        Allure.addAttachment("Response body", response.body().prettyPrint());
        Allure.addAttachment("Status code", String.valueOf(response.statusCode()));
        return response;
    }

        @Step
        public static void isBodyContains(Response response, String expectedResult)
        {
            response.then().assertThat().body(containsString(expectedResult));
        }

        @Step
        public static void isBodyContainsValue(Response response, String key, String expectedResult){
        response.then().assertThat().body(key, containsString(expectedResult));

        }
        @Step
    public static void isexpectedemail(Response response, String Expextedemail)
        {
            assertTrue(response.getBody().asString().contains(Expextedemail));
        }

    }

