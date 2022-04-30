package cookalone.main.domain.dto;

import cookalone.main.domain.status.Gender;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;

/**
 * Dto 에는 @Data 필수
 */
@Data
public class UserDto {
    private String email;
    private String password;

    @NotEmpty(message = "회원 이름은 필수 입니다.")
    private String nickname;
    private String username;
    private String city;
    private String street;
    private String zipcode;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String birthDate;
    private String createdDate;
    private String modifiedDate;
}
