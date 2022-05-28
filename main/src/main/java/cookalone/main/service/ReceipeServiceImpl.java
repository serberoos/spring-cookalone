package cookalone.main.service;

import cookalone.main.domain.Receipe;
import cookalone.main.domain.User;
import cookalone.main.domain.dto.receipe.ReceipeDto;
import cookalone.main.repository.ReceipeRepository;
import cookalone.main.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ReceipeServiceImpl implements ReceipeService {
    private final ReceipeRepository receipeRepository;
    private final UserRepository userRepository;

    /* 레시피 생성 */
    @Transactional
    public Long saveReceipe(@RequestBody ReceipeDto.Request receipeDto, String nickname) {
        User user = userRepository.findByNickname(nickname);
        receipeDto.setUser(user);

        return receipeRepository.save(receipeDto.toEntity()).getId();
    }

    /* 레시피 조회 */
    @Transactional
    public ReceipeDto.Response getReceipeDetail(Long id) {
        return null;
    }

    /* 레시피 삭제 */
    @Transactional
    public boolean deleteReceipe(Long id) {
        return false;
    }

    @Transactional
    public List<Receipe> searchReceipe(String keyword) {
        List<Receipe> receipes = receipeRepository.findByTitleContaining(keyword);
        return receipes;
    }

    /* 레시피 리스트 페이징 조회*/
    @Transactional(readOnly = true)
    public Page<Receipe> getReceipePages(Pageable pageable) {
        int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1); // page는 index 처럼 0부터 시작
        pageable = PageRequest.of(page, 9);

        return receipeRepository.findAll(pageable);
    }


}
