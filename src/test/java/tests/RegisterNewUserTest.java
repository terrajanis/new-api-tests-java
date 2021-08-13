package tests;

import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Title;

import static factories.UserFactory.getUser;
import static steps.UserSteps.registerUser;

public class RegisterNewUserTest extends BaseTest{

    @Test
    @Description("Send post request to register a new user")
    @Title("Register a new user")
    public void registerNewUser(){
        registerUser(BASE_URL, getUser());
    }
}
