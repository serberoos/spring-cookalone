package cookalone.main.validator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
/**
 * @SuppressWarnings("unchecked") : 컴파일러에서 경고 해제
 */
@Slf4j
public abstract class OverlapAbstractValidator<T> implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void validate(Object target, Errors errors) {
        try {
            doValidate((T) target, errors);
        } catch (RuntimeException e){
            log.error("중복 검증 에러", e);
            throw e;
        }
    }
    /* 검증 로직이 들어갈 부분 추상화 메소드 */
    protected abstract void doValidate(final T dto, final Errors errors);
}
