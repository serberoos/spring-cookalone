package cookalone.main.validator;

import cookalone.main.domain.dto.account.UserRequestDto;
import cookalone.main.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class UserRequestDtoValidator implements Validator {

    private final UserServiceImpl userService;
    /**
     * 검증하려는 클래스를 체크한다.
     */
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(UserRequestDto.class);
    }

    /**
     * 검증
     */
    @Override
    public void validate(Object target, Errors errors) {
        UserRequestDto userRequestDto = (UserRequestDto) target;

        //NotEmpty
        if(!ObjectUtils.isEmpty(userRequestDto.getEmail())) {
            errors.rejectValue("email", "이메일을 입력하세요.");
        }
    }
}