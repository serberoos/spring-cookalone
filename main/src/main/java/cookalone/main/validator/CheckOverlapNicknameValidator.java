package cookalone.main.validator;

import cookalone.main.domain.dto.account.UserRequestDto;
import cookalone.main.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@RequiredArgsConstructor
@Component
public class CheckOverlapNicknameValidator extends OverlapAbstractValidator<UserRequestDto>{

    private final UserRepository userRepository;

    @Override
    protected void doValidate(UserRequestDto dto, Errors errors) { /* 검증 로직 */
        if (userRepository.existsByNickname(dto.toEntity().getNickname())){
            errors.rejectValue("nickname", "닉네임 중복 오류", "이미 사용중인 닉네임 입니다.");
        }
    }
}
