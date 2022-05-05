package cookalone.main.auth;

import cookalone.main.domain.User;
import cookalone.main.domain.dto.account.UserDto;
import cookalone.main.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;


@Service
@Slf4j
@RequiredArgsConstructor
public class PrincipalUserDetailService implements UserDetailsService {
    private final UserRepository userRepository;
    private final HttpSession session;


    /* Email이 DB에 있는지 확인한다. */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {   // 스프링이 로그인 요청을 가로챌 때, username,password 변수 2개를 가로채는데 password 부분 처리는 알아서 하고 해당 username이 DB에 존재하는지 확인해서 리턴해주면 됨.
        User user = userRepository.findByEmail(email).orElseThrow(() ->
                new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다. :" + email));

        session.setAttribute("user", new UserDto.Response(user));

        /* 시큐리티 세션에 유저 정보 저장 */
        return new PrincipalUserDetail(user);
    }
}
