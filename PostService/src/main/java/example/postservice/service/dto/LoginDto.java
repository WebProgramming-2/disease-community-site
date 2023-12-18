package example.postservice.service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class LoginDto {
    private String username; // loginId
    private String password;
}
