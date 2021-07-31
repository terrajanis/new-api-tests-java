package helpers;

import io.restassured.response.Response;
import org.testng.Assert;
import ru.yandex.qatools.allure.annotations.Step;

public class AssertHelper {

    @Step("Check a response status code")
    public static void checkStatusCode(Response response, int expectedStatusCode){
        int actualStatusCode = response.getStatusCode();
        Assert.assertEquals(actualStatusCode, expectedStatusCode, "Status code is wrong for the response: " + response.asString());
    }
}
