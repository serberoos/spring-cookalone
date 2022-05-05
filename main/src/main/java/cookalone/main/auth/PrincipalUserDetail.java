package cookalone.main.auth;

import cookalone.main.domain.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 스프링 시큐리티가 로그인 요청을 가로채 로그인을 진행하고 완료되면 UserDetails 타입의 오브젝트를 스프링 시큐리티의 고유한 세션 저장소에 저장한다.
 * => UserDetails 타입의 PrincipalUserDetails가 저장됨
 */
@AllArgsConstructor
public class PrincipalUserDetail implements UserDetails {
    private User user; // 콤포지션

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername(){
        return user.getUsername();
    }
    /**
     * 계정 만료 여부
     * true : 만료 안됨.
     * false : 만료.
     */
    @Override
    public boolean isAccountNonExpired(){
        return true;
    }

    /**
     * 계정 잠김 여부
     * true : 잠기지 않음.
     * false : 잠김.
     */
    @Override
    public boolean isAccountNonLocked(){
        return true;
    }

    /**
     * 비밀번호 만료 여부
     * true : 만료 안됨.
     * false : 만료
     */
    @Override
    public boolean isCredentialsNonExpired(){
        return true;
    }
    /**
     * 사용자 활성화 여부
     * true : 만료 안됨
     * false : 만료
     */
    @Override
    public boolean isEnabled(){
        return true;
    }

    /* 유저의 권한 목록 (원래는 권한이 여러개 있을 수 있으므로 루프를 돌아야 하는데 한개만 */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        Collection<GrantedAuthority> collectors = new ArrayList<>();
        collectors.add(()-> { return "ROLE_" + user.getRole();});

        return collectors;
    }
}
