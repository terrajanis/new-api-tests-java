package models;

import lombok.Builder;
import lombok.Data;
import org.testng.Assert;


@Data
@Builder
public class User {

    private String email;

    private String password;

    private String username;

    private String lastName;

    private String firstName;

    public void compareWithResponse(UserFromResponse userFromResponse) {
        Assert.assertEquals(this.getEmail(), userFromResponse.getEmail(), "Emails aren't equal");
        Assert.assertEquals(this.getUsername(), userFromResponse.getUsername(), "Usernames aren't equal");
        Assert.assertEquals(this.getLastName(), userFromResponse.getLastName(), "Last names aren't equal");
        Assert.assertEquals(this.getFirstName(), userFromResponse.getFirstName(), "First names aren't equal");
    }
}
