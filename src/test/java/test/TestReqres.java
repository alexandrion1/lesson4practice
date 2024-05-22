package test;

import io.qameta.allure.Allure;
import io.restassured.response.Response;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static utils.Steps.*;

public class TestReqres {
    @BeforeSuite
    public void setUP()
    {
        baseURI = "https://reqres.in/api";

    }
    @Test
    public void testlistusers()
    {
        String url = "/users?page=2";
        Allure.addAttachment("URL", url);
        Response response = GET(url);
        isstatuscodevalid(response,200);
        isexpectedemail(response,"michael.lawson@reqres.in");

    }
    @Test
    public void testsingleuser()
    {
        String url = "/users/2";
        Allure.addAttachment("URL", url);
        Response response = GET(url);
        isstatuscodevalid(response,200);
        isBodyContainsValue(response,"data.last_name","Weaver");
    }
    @Test
    public void testCreateuser()
    {
        String url = "/users";
        String contenttype = "application/json";
        String body = "{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"job\": \"leader\"\n" +
                "}";
        Response response = POST(body, url, contenttype);
        isstatuscodevalid(response, 201);
        isBodyContainsValue(response, "name", "morpheus");


    }
    @Test
    public void testUpdateuser()
    {
        String url = "/users";
        String contenttype = "application/json";
        String body = "{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"job\": \"QA Automation leader Patch\"\n" +
                "}";
        Response response = PATCH(body, url, contenttype);
        isstatuscodevalid(response, 201);
        isBodyContainsValue(response, "name", "morpheus");
    }
    @Test
    public void testUpdatuser()
    {
        String url = "/users";
        String contenttype = "application/json";
        String body = "{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"job\": \"QA Automation leader Put\"\n" +
                "}";
        Response response = PUT(body, url, contenttype);
        isstatuscodevalid(response, 201);
        isBodyContainsValue(response, "job", "QA Automation leader Put");

    }

    @Test
    public void testDeleteuser()
    {
        String url = "/users/2";
        String body = "{\n" +
                "        \"id\": 2,\n" +
                "        \"email\": \"janet.weaver@reqres.in\",\n" +
                "        \"first_name\": \"Janet\",\n" +
                "        \"last_name\": \"Weaver\",\n" +
                "        \"avatar\": \"https://reqres.in/img/faces/2-image.jpg\"\n" +
                "    }";

        Response response = DELETE(body, url);
        isstatuscodevalid(response, 204);
    }


}
