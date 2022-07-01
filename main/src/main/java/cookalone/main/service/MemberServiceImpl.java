package cookalone.main.service;

import cookalone.main.domain.Member;
import cookalone.main.domain.dto.account.MemberRequestDto;
import cookalone.main.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @RequiredArgsConstructor : final 붙은 필드 생성자를 생성해서 값을 자동으로 넣어줌.
 */
@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {


    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder encoder;

    /* 회원가입 시 유효성 체크 */
    public Map<String, String> validateHandling(Errors errors){
        Map<String, String> validatorResult = new HashMap<>();

        /* 유효성 및 중복 검사 실패 필드 목록을 받는다. */
        for (FieldError error : errors.getFieldErrors()) {
            String validKeyName = String.format("valid_%s", error.getField());
            validatorResult.put(validKeyName, error.getDefaultMessage());
        }
        return validatorResult;
    }

    /**
     * 유저
     * 회원가입
     * @Transcational : Default : readOnly = false
     */
    @Transactional
    public Long join(MemberRequestDto memberRequestDto){
        memberRequestDto.setPassword(encoder.encode(memberRequestDto.getPassword())); // 사용자 비밀번호를 해쉬 암호화 후 레포지토리에 저장한다.
        return memberRepository.save(memberRequestDto.toEntity()).getId();
    }

    //회원 전체 조회
    public List<Member> findUsers() {
        return memberRepository.findAll();
    }
}
