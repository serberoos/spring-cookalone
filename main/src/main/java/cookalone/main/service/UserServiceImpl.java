package cookalone.main.service;

import cookalone.main.domain.User;
import cookalone.main.domain.dto.account.UserRequestDto;
import cookalone.main.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @RequiredArgsConstructor : final 붙은 필드 생성자를 생성해서 값을 자동으로 넣어줌.
 */
@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;

    private final BCryptPasswordEncoder encoder;

    /**
     * 회원가입
     * @Transcational : Default : readOnly = false
     */
    @Transactional
    public Long join(UserRequestDto userDto){
        userDto.setPassword(encoder.encode(userDto.getPassword())); // 사용자 비밀번호를 해쉬 암호화 후 레포지토리에 저장한다.
        return userRepository.save(userDto.toEntity()).getId();
    }

    //회원 전체 조회
    public List<User> findUsers() {
        return userRepository.findAll();
    }
}
