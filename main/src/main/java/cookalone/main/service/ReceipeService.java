package cookalone.main.service;

import cookalone.main.domain.User;
import cookalone.main.domain.dto.receipe.ReceipeDto;
import cookalone.main.repository.ReceipeRepository;
import cookalone.main.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

@Slf4j
@RequiredArgsConstructor
@Service
public class ReceipeService {
    private final ReceipeRepository receipeRepository;
    private final UserRepository userRepository;

    /* 레시피 생성 */
    @Transactional
    public Long save(@RequestBody ReceipeDto.Request receipeDto, String nickname) {
        User user = userRepository.findByNickname(nickname);
        receipeDto.setUser(user);

        return receipeRepository.save(receipeDto.toEntity()).getId();
    }


}
