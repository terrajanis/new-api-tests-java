package models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {

    private String email;

    private String password;

    private String username;

    private String lastName;

    private String firstName;
}