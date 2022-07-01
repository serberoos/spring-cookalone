package cookalone.main.validator;

import cookalone.main.domain.dto.account.MemberRequestDto;
import cookalone.main.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@RequiredArgsConstructor
@Component
public class CheckOverlapUserNicknameValidator extends OverlapAbstractValidator<MemberRequestDto>{

    private final MemberRepository memberRepository;

    @Override
    protected void doValidate(MemberRequestDto dto, Errors errors) { /* 검증 로직 */
        if (memberRepository.existsByNickname(dto.toEntity().getNickname())){
            errors.rejectValue("nickname", "닉네임 중복 오류", "이미 사용중인 닉네임 입니다.");
        }
    }
}
