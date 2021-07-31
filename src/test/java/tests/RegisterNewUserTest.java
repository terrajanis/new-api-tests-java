package tests;

import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Title;
import steps.UserSteps;

import static factories.UserFactory.getUser;

public class RegisterNewUserTest extends BaseTest{

    @Test
    @Description("Send post request to register a new user")
    @Title("Register a new user")
    public void registerNewUser(){
        UserSteps.registerUser(BASE_URL, getUser());
    }


}
