package cookalone.main.oauth;

import cookalone.main.domain.User;
import cookalone.main.domain.status.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class OAuthAttributes {
    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String username;
    private String email;
    private Role role;

    public static OAuthAttributes of(String registrationId,
                                     String userNameAttributeName,
                                     Map<String, Object> attributes) {
        /* 구글 / 네이버 / 카카오 구분을 위한 메소드 (ofNaver, ofKakao) */
        if ("naver".equals(registrationId)) {
            return ofNaver("id", attributes);
        }

        return ofGoogle(userNameAttributeName, attributes);
    }
    private static OAuthAttributes ofGoogle(String userNameAttributeName,
                                            Map<String, Object> attributes){
        return OAuthAttributes.builder()
                .email((String) attributes.get("email")) // 로그인 아이디는 이메일로 저장한다.
                .username((String) attributes.get("name"))
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }
    private static OAuthAttributes ofNaver(String userNameAttributeName, Map<String, Object> attributes){
        /* JSON 형태이기 때문에 Map을 통해 데이터를 가져온다. */
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");

        log.info("naver response: " + response);

        return OAuthAttributes.builder()
                .email((String) response.get("email"))
                .username((String) attributes.get("name"))
                .attributes(response)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    public User toEntity() {
        return User.builder()
                .username(username)
                .email(email)
                .role(Role.SOCIAL)
                .build();
    }

}
