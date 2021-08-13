package steps;

import com.fasterxml.jackson.databind.ObjectMapper;
import helpers.ApiHelper;
import helpers.AssertHelper;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import models.User;
import models.UserFromResponse;
import org.apache.http.HttpStatus;
import org.testng.Assert;

import java.util.HashMap;
import java.util.Map;

public class UserSteps {

    @Step("Register a new user and get id")
    public static Integer registerUser(String baseUrl, User user) {
        ObjectMapper mapper = new ObjectMapper();
        Response response = ApiHelper.post(baseUrl + "/user/", mapper.convertValue(user, Map.class));
        AssertHelper.checkStatusCode(response, HttpStatus.SC_OK);
        Assert.assertNotNull(response.jsonPath().get("id"));
        return Integer.parseInt(response.jsonPath().get("id"));
    }

    @Step("Login user")
    public static Response loginUser(String baseUrl, Integer userId, User user) {
        Map<String, String> loginData = new HashMap<>();
        loginData.put("email", user.getEmail());
        loginData.put("password", user.getPassword());
        Response response = ApiHelper.post(baseUrl + "/user/login", loginData);
        AssertHelper.checkStatusCode(response, HttpStatus.SC_OK);
        Integer userIdFromResponse = response.jsonPath().get("user_id");
        Assert.assertEquals(userIdFromResponse, userId, "User ids aren't equal");
        return response;
    }

    @Step("Get information about the user")
    public static UserFromResponse getUserInfoFromResponse(String baseUrl, Integer userId, Map<String, String> token, Map<String, String> cookie, Boolean isDeleted) {
        Response responseWithUserData = ApiHelper.get(baseUrl + String.format("/user/%s", userId), token, cookie);
        if(isDeleted) {
            AssertHelper.checkStatusCode(responseWithUserData, HttpStatus.SC_NOT_FOUND);
            return null;
        } else {
            AssertHelper.checkStatusCode(responseWithUserData, HttpStatus.SC_OK);
            AssertHelper.checkResponseJsonSchema(responseWithUserData, UserFromResponse.class);
            return responseWithUserData.as(UserFromResponse.class);
        }
    }
}
