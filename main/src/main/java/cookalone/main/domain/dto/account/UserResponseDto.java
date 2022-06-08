package cookalone.main.domain.dto.account;

import cookalone.main.domain.Address;
import cookalone.main.domain.User;
import cookalone.main.domain.status.Gender;
import cookalone.main.domain.status.Role;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 인증된 사용자 정보를 세션에 저장하기 위한 클래스
 * 세션을 저장하기 위해 User 엔티티 클래스를 직접 사용하게 되면 직렬화를 해야 하는데,
 * 엔티티 클래스에 직렬화를 넣어주면 추후에 다른 엔티티와 연관관계를 맺을시
 * 직렬화 대상에 다른 엔티티까지 포함될 수 있어 성능 이슈 우려가 있기 때문에
 * 성능 이슈, 부수효과 우려가 있다.
 * 세션 저장용 Dto 클래스 생성
 *
 * UserResponseDto : 회원 Service 응답(Response) DTO 클래스
 * */
@Getter
public class UserResponseDto implements Serializable {
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
    public UserResponseDto(User user){
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
