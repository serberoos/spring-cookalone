package cookalone.main.validator;

import cookalone.main.domain.dto.account.MemberRequestDto;
import cookalone.main.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@RequiredArgsConstructor
@Component
public class CheckOverlapUserEmailValidator extends OverlapAbstractValidator<MemberRequestDto> {
    private final MemberRepository memberRepository;

    @Override
    protected void doValidate(MemberRequestDto dto, Errors errors) {
        if (memberRepository.existsByEmail(dto.toEntity().getEmail())){
            errors.rejectValue("email", "이메일 중복 오류", "이미 사용중인 이메일 입니다.");
        }
    }
}
