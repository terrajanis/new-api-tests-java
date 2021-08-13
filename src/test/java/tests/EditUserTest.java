package tests;

import helpers.ApiHelper;
import helpers.AssertHelper;
import io.restassured.response.Response;
import models.User;
import models.UserFromResponse;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Title;
import steps.UserSteps;

import java.util.Collections;
import java.util.Map;

import static factories.UserFactory.getUser;
import static steps.UserSteps.getUserInfoFromResponse;
import static steps.UserSteps.loginUser;

public class EditUserTest extends BaseTest {

    private User user;

    @BeforeClass
    public void getUserData() {
        user = getUser();
    }

    @Test
    @Description("Send put request to edit a user info")
    @Title("Edit information about a user")
    public void editUserInfo() {
        Integer userId = UserSteps.registerUser(BASE_URL, user);
        Response responseWithLoginData = loginUser(BASE_URL, userId, user);
        Map<String, String> token = Collections.singletonMap("x-csrf-token", responseWithLoginData.getHeader("x-csrf-token"));
        Map<String, String> cookie = Collections.singletonMap("auth_sid", responseWithLoginData.getCookie("auth_sid"));
        String firstName = "Newname";
        Map<String, String> params = Collections.singletonMap("firstName", firstName);
        Response response = ApiHelper.put(BASE_URL + String.format("/user/%s", userId),token, cookie, params);
        AssertHelper.checkStatusCode(response, HttpStatus.SC_OK);
        UserFromResponse userFromResponse = getUserInfoFromResponse(BASE_URL, userId, token, cookie, false);
        Assert.assertEquals(userFromResponse.getFirstName(), firstName);
    }
}
