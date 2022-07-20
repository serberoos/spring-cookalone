package cookalone.main.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.http.HttpSession;
import java.util.Collection;
import java.util.Optional;

@Configuration
@EnableJpaAuditing
public class SpringSecurityAuditorAware implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        /**
         * SecurityContext에서 인증 정보를 가져와 주입한다.
         * 현 코드는 Context 유저가 User인가 권한이 있으면, 해당 Principal name을 대입하고, 아니면 Null을 Set한다.
         */
        return Optional.ofNullable(SecurityContextHolder.getContext())
                .map(SecurityContext::getAuthentication)
                .map(authentication -> {
                    Collection<? extends GrantedAuthority> auth = authentication.getAuthorities();

//                    boolean isUser = auth.contains(new SimpleGrantedAuthority("ROLE_USER"));
//                    boolean isAdmin = auth.contains(new SimpleGrantedAuthority("ROLE_ADMIN"));
//                    boolean isSocial = auth.contains(new SimpleGrantedAuthority("ROLE_SOCIAL"));
//                    System.out.println("@" + isUser + "@" + isAdmin + "@" + isSocial);
//                    System.out.println(authentication.getName());
//                    if (isUser || isAdmin || isSocial) {
                    return authentication.getName();
//                    }
//                    return null;
                });
    }
}
