package factories;

import models.User;
import org.apache.commons.lang3.RandomUtils;

import static utils.RandomUtils.getRandomEmail;


public class UserFactory {

    public static User getUser() {

        return User.builder().
                email(getRandomEmail()).
                password("Qwasz1").
                username("Username"+ RandomUtils.nextInt(0, 100)).
                lastName("Ivanova").
                firstName("Maria").
                build();
    }
}
