package cookalone.main.validator;

import cookalone.main.domain.User;
import cookalone.main.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class UserValidator implements Validator {

    private final UserServiceImpl userService;

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Object user = (User) target;

        // 검증 로직
        if (ObjectUtils.isEmpty(user.(user.get))) {
            errors.put("id", "이메일을 입력하세요")

        }

    }
}
