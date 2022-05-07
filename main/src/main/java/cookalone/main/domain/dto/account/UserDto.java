package cookalone.main.domain.dto.account;

import cookalone.main.domain.Address;
import cookalone.main.domain.User;
import cookalone.main.domain.status.Gender;
import cookalone.main.domain.status.Role;
import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * request, response DTO 클래스들을 하나로 묶어 InnerStaticClass로 한번에 관리합니다.
 * @NotBlank 공백 비 허용
 */

public class UserDto {

    /* 회원 Service 요청(Request) DTO 클래스 */

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Request {

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

    /**
     * 인증된 사용자 정보를 세션에 저장하기 위한 클래스
     * 세션을 저장하기 위해 User 엔티티 클래스를 직접 사용하게 되면 직렬화를 해야 하는데,
     * 엔티티 클래스에 직렬화를 넣어주면 추후에 다른 엔티티와 연관관계를 맺을시
     * 직렬화 대상에 다른 엔티티까지 포함될 수 있어 성능 이슈 우려가 있기 때문에
     * 성능 이슈, 부수효과 우려가 있다.
     * 세션 저장용 Dto 클래스 생성
     * */
    @Getter
    public static class Response implements Serializable {
        private Long id;
        private String email;
        private String password;
        private String nickname;
        private String username;
        private String birthDate;
        private Gender gender;
        private Address address;
        private Role role;
        private LocalDateTime termsAgreeDate;
        private LocalDateTime createdDate;
        private LocalDateTime modifiedDate;

        /* Entity -> Dto */
        public Response(User user){
            this.id = user.getId();
            this.email = user.getEmail();
            this.password = user.getPassword();
            this.nickname = user.getNickname();
            this.username = user.getUsername();
            this.birthDate = user.getBirthDate();
            this.gender = user.getGender();
            this.address = user.getAddress();
            this.role = user.getRole();
            this.termsAgreeDate = user.getTermsAgreeDate();
            this.createdDate = user.getCreatedDate();
            this.modifiedDate = user.getModifiedDate();
        }
    }
}
