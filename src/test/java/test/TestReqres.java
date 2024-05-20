package test;

import io.qameta.allure.Allure;
import io.restassured.response.Response;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
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
        isBodyContainsValue(response, "data.email", "michael.lawson@reqres.in");

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
        String body = "{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"job\": \"leader\"\n" +
                "}";
        Response response = POST(body, url);
        isstatuscodevalid(response, 201);
    }
    @Test
    public void testUpdateuser()
    {
        String url = "/users";
        String body = "{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"job\": \"QA Automation leader Patch\"\n" +
                "}";
        Response response = PATCH(body, url);
        isstatuscodevalid(response, 201);
        isBodyContains(response, "id");
    }
    @Test
    public void testUpdatuser()
    {
        String url = "/users";
        String body = "{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"job\": \"QA Automation leader Put\"\n" +
                "}";
        Response response = PUT(body, url);
        isstatuscodevalid(response, 201);
        isBodyContains(response, "id");
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
