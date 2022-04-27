package cookalone.main.domain.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class UserDto {
    private String email;
    private String password;

    @NotEmpty(message = "회원 이름은 필수 입니다.")
    private String nickname;
    private String username;
    private String city;
    private String street;
    private String zipcode;
    private String createdDate;
    private String modifiedDate;
}
