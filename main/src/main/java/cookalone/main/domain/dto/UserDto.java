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
    @NotEmpty(message = "이메일을 입력하세요.")
    private String email;
    @NotEmpty(message = "영문/숫자/특수문자 2가지 이상 조합 (8~20자)")
    private String password;

    @NotEmpty(message = "닉네임을 입력하세요.")
    private String nickname;

    @NotEmpty(message = "이름을 입력하세요.")
    private String username;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @NotEmpty(message = "생년월일을 입력하세요.")
    private String birthDate;

    private String city;
    private String street;
    private String zipcode;

    private String createdDate;
    private String modifiedDate;
}
