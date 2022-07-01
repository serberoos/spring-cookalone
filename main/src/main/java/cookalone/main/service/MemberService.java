package cookalone.main.service;

import cookalone.main.domain.Member;
import cookalone.main.domain.dto.account.MemberRequestDto;

import java.util.List;

public interface MemberService {
    public Long join(MemberRequestDto userDto);
    public List<Member> findUsers();
}
