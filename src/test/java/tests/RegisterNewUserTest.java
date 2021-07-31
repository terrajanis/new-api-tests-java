package tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import helpers.ApiHelper;
import helpers.AssertHelper;

import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.Assert;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Title;

import java.util.Map;

import static factories.UserFactory.getUser;

public class RegisterNewUserTest extends BaseTest{

    @Test
    @Description("Send post request to register a new user")
    @Title("Register a new user")
    public void registerNewUser(){
        ObjectMapper mapper = new ObjectMapper();
        Response response = ApiHelper.post(BASE_URL + "/user/", mapper.convertValue(getUser(), Map.class));
        AssertHelper.checkStatusCode(response, HttpStatus.SC_OK);
        Assert.assertNotNull(response.jsonPath().get("id"));
    }
}
