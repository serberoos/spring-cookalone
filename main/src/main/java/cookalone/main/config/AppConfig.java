package cookalone.main.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ModelMapper : 반복적인 object간 변환을 간단하게 줄이고 싶을 때 사용.
 *
 * Entity와 view layer에서 사용될 data object(Dto)가 구분된다.
 * 이때 같은 필드를 자동으로 매핑해준다.
 */
@Configuration
public class AppConfig {

    @Bean // 자주쓰므로 Bean에 등록해주었음.
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
