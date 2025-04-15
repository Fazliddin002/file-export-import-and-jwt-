package realproject.security.dto;

import lombok.Data;

@Data
public class UserDto{

    private String phone;

    private String password;

    private String passwordRepeat;

   private String firstName;

   private   String lastName;

}
