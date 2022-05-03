package cookalone.main.domain.dto.account;

import cookalone.main.domain.Address;
import cookalone.main.domain.User;
import cookalone.main.domain.status.Gender;
import cookalone.main.domain.status.Role;
import lombok.Builder;
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

    private Role role;

    private String termsAgreeDate;
    private String createdDate;
    private String modifiedDate;

    public User toEntity() {
        Address address = Address.builder()
                .city(city)
                .street(street)
                .zipcode(zipcode)
                .build();

        User user = User.builder()
                .username(email)
                .password(password)
                .nickname(nickname)
                .username(username)
                .birthDate(birthDate)
                .gender(gender)
                .address(address)
                .role(role.USER)
                .build();
        return user;
    }
}
