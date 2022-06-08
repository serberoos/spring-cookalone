package cookalone.main.domain.dto.account;

import cookalone.main.domain.Address;
import cookalone.main.domain.User;
import cookalone.main.domain.status.Gender;
import cookalone.main.domain.status.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;

/**
 * UserRequestDto : 회원 Service 요청(Request) DTO 클래스
 * @NotBlank 공백 비 허용
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequestDto {
    private Long id;

    @NotEmpty(message = "이메일을 입력하세요.")
    private String email;

    @NotEmpty(message = "영문/숫자/특수문자 2가지 이상 조합 (8~20자)")
    private String password;

    @NotEmpty(message = "닉네임을 입력하세요.")
    private String nickname;

    @NotEmpty(message = "이름을 입력하세요.")
    private String username;

    @NotEmpty(message = "생년월일을 입력하세요.")
    private String birthDate;

    @Enumerated(EnumType.STRING)
    private Gender gender;

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
                .id(id)
                .email(email)
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
