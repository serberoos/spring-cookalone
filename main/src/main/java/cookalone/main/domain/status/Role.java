package cookalone.main.domain.status;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @RequiredArgsConstructor: 초기화 되지 않은 final이나 @NonNull이 붙은 필드에 대해 생성자를 생성한다.
 */
@Getter
@RequiredArgsConstructor
public enum Role {
    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN"),
    SOCIAL("ROLE_SOCIAL"); // OAuth

    private final String value;
}
