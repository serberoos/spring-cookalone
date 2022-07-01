package cookalone.main.service;

import cookalone.main.domain.Receipe;
import cookalone.main.domain.Member;
import cookalone.main.domain.dto.receipe.ReceipeRequestDto;
import cookalone.main.domain.dto.receipe.ReceipeResponseDto;
import cookalone.main.repository.ReceipeRepository;
import cookalone.main.repository.MemberRepository;
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
    private final MemberRepository memberRepository;

    /* 레시피 생성 */
    @Transactional
    public Long saveReceipe(@RequestBody ReceipeRequestDto receipeDto, String nickname) {
        Member member = memberRepository.findByNickname(nickname);
        receipeDto.setMember(member);

        return receipeRepository.save(receipeDto.toEntity()).getId();
    }
    /* 레시피 검색 */
    @Transactional
    public List<Receipe> searchReceipe(String keyword) {
        List<Receipe> receipeList = receipeRepository.findByTitleContaining(keyword);

        return receipeList;
    }
    /* 레시피 조회 | readOnly = true 속성으로 조회 속도를 개선함.*/
    @Transactional(readOnly = true)
    public ReceipeResponseDto getReceipeDetail(Long id) {
        Receipe receipe = receipeRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("레시피가 존재하지 않습니다. id:" + id));
        return new ReceipeResponseDto(receipe);
    }

    /* 레시피 삭제 */
    @Transactional
    public boolean deleteReceipe(Long id) {
        return false;
    }

    /* 레시피 리스트 페이징 조회*/
    @Transactional(readOnly = true)
    public Page<Receipe> getReceipePages(Pageable pageable) {
        int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1); // page는 index 처럼 0부터 시작
        pageable = PageRequest.of(page, 9);

        return receipeRepository.findAll(pageable);
    }


}
