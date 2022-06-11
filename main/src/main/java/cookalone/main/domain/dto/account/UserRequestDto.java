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
import javax.validation.constraints.*;

/**
 * UserRequestDto : 회원 Service 요청(Request) DTO 클래스
 *
 * @NotBlank 공백 비 허용
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequestDto {
    private Long id;

    @NotBlank(message = "이메일을 입력하세요.")
    @Email(message = "이메일 주소를 다시 확인하세요.")
    private String email;


    /**
     * (?=.*[0-9]) : 숫자가 적어도 하나는 포함되어야 함.
     * (?=.*[a-zA-Z]) : 영문 대/소문자 중 적어도 하나는 포함되어야 함.
     * (?=.*\\W) : 특수문자가 적어도 하나는 포함되어야 함.
     * (?=\\S+$) : 공백 제거
     */
    @NotBlank(message = "비밀번호를 입력하세요.")
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\\\W)(?=\\\\S+$).{8,20}",
            message = "영문/숫자/특수문자가 모두 들어간 조합 (8~20자)")
    private String password;

    @NotBlank(message = "닉네임을 입력하세요.")
    @Pattern(regexp = "^[가-힣ㄱ-ㅎa-zA-Z0-9._ -]{2,10}$",
            message = "영문/숫자/한글/공백/언더스코어 가능 (2~10자)")
    private String nickname;

    @NotBlank(message = "이름을 입력하세요.")
    @Pattern(regexp = "^[가-힣ㄱ-ㅎ]{2,4}$",
            message = "한글 명(2~4자)")
    private String username;

    @NotBlank(message = "생년월일을 입력하세요.")
    @Pattern(regexp = "(19|20)\\\\d{2}(0[1-9]|1[012])(0[1-9]|[12][0-9]|3[01])",
            message = "YYYYMMDD 포맷으로 입력하세요.")
    private String birthDate;

    @NotNull(message = "성별을 선택하세요.")
    @Enumerated(EnumType.STRING)
    private Gender gender; // 고수준 valid 필요

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
