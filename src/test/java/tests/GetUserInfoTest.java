package tests;

import helpers.ApiHelper;
import helpers.AssertHelper;
import io.restassured.response.Response;
import models.User;
import models.UserFromResponse;
import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Title;
import steps.UserSteps;

import java.util.Collections;
import java.util.Map;

import static factories.UserFactory.getUser;
import static steps.UserSteps.loginUser;

public class GetUserInfoTest extends BaseTest {

    private User user;

    @BeforeClass
    public void getUserData() {
        user = getUser();
    }

    @Test
    @Description("Send get request to get a user info")
    @Title("Get information about a user")
    public void getUserInfo() {
        Integer userId = UserSteps.registerUser(BASE_URL, user);
        Response responseWithLoginData = loginUser(BASE_URL, userId, user);
        Map<String, String> token = Collections.singletonMap("x-csrf-token", responseWithLoginData.getHeader("x-csrf-token"));
        Map<String, String> cookie = Collections.singletonMap("auth_sid", responseWithLoginData.getCookie("auth_sid"));
        Response responseWithUserData = ApiHelper.get(BASE_URL + String.format("/user/%s", userId),token, cookie);
        AssertHelper.checkStatusCode(responseWithUserData, HttpStatus.SC_OK);
        AssertHelper.checkResponseJsonSchema(responseWithUserData, UserFromResponse.class);
        UserFromResponse userFromResponse = responseWithUserData.as(UserFromResponse.class);
        user.compareWithResponse(userFromResponse);
    }


}
