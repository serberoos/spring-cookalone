package cookalone.main.service;

import cookalone.main.domain.User;
import cookalone.main.domain.dto.account.UserDto;
import cookalone.main.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @RequiredArgsConstructor : final 붙은 필드 생성자를 생성해서 값을 자동으로 넣어줌.
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    /**
     * 회원가입
     * @Transcational : Default : readOnly = false
     */
    @Transactional
    public Long join(UserDto userDto){

        User user = userDto.toEntity();
        validateDuplicateNickname(user); // 중복 회원을 검증한다.
        userRepository.saveUser(user);
        return user.getId();
    }

    private void validateDuplicateNickname(User user){
        //EXCEPTION
        List<User> findUsers = userRepository.findByNickname(user.getNickname()); //nickname을 유니크 제약 조건으로 잡아주어야 함.
        if (!findUsers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 닉네임 입니다.");
        }
    }

    //회원 전체 조회

    public List<User> findUsers() {
        return userRepository.findAll();
    }

    public User findOne(Long userId){
        return userRepository.findOne(userId);
    }
}
