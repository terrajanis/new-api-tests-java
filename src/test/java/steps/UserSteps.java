package steps;

import com.fasterxml.jackson.databind.ObjectMapper;
import helpers.ApiHelper;
import helpers.AssertHelper;
import io.restassured.response.Response;
import models.User;
import org.apache.http.HttpStatus;
import org.testng.Assert;

import java.util.HashMap;
import java.util.Map;

public class UserSteps {

    public static Integer registerUser(String baseUrl, User user) {
        ObjectMapper mapper = new ObjectMapper();
        Response response = ApiHelper.post(baseUrl + "/user/", mapper.convertValue(user, Map.class));
        AssertHelper.checkStatusCode(response, HttpStatus.SC_OK);
        Assert.assertNotNull(response.jsonPath().get("id"));
        return Integer.parseInt(response.jsonPath().get("id"));
    }

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
}
