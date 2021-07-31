package models;

import com.github.imifou.jsonschema.module.addon.annotation.JsonSchema;
import lombok.Data;

@Data
public class UserFromResponse {

    @JsonSchema(required = true)
    private String id;

    @JsonSchema(required = true)
    private String email;

    @JsonSchema(required = true)
    private String username;

    @JsonSchema(required = true)
    private String lastName;

    @JsonSchema(required = true)
    private String firstName;
}
