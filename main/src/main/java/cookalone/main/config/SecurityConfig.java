package cookalone.main.config;

import cookalone.main.auth.PrincipalUserDetailService;
import cookalone.main.oauth.CustomOAuth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.CustomUserTypesOAuth2UserService;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

/**
 * @Configuration : 빈 등록
 * @EnableWebSecurity : 스프링 시큐리티 설정임을 정의 (시큐리티 필터가 등록됨)
 * @EnableGlobalMethodSecurity(prePostEnabled = true) : 특정 주소로 접근시 권한 및 인증을 미리 체크 한다.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) // 특정 주소로 접근시 권한 및 인증을 미리 체크 한다.
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private final PrincipalUserDetailService principalUserDetailsService;

    /* 로그인 실패 핸들러 의존성 주입 */
    private final AuthenticationFailureHandler authFailureHandler;

    /* OAuth */
    private final CustomOAuth2UserService customOAuth2UserService;

    /**
     * BCryptPasswordEncoder 스프링 시큐리티에서 제공하는 비밀번호 암호화 객체
     * 비밀번호를 암호화해 사용할 수 있도록 Bean으로 등록한다.
     */
    @Bean
    public BCryptPasswordEncoder encodePassword(){
        return new BCryptPasswordEncoder();
    }

    /**
     * SpringSecurity에서 모든 인증처리는 AuthenticationManager를 통해 이루어 지며 이는 AuthenticationBuilder로 생성한다.
     * 로그인 인증을 위해 MyUserDetailsService에서 UserDetailService를 implements 하여 loadUserByUsername() 메소드를 구현했다.
     * AuthenticationManager에게 어떤 해쉬로 암호화 했는지 알려주기 위해 passwordEncoder를 사용했다.
     * => 시큐리티가 대신 로그인 해줄때 password를 가로채가는데 해당 password가 어떤 값으로 해쉬가 되어 회원가입이 되었는지 알아야
     * 같은 해쉬로 암호화해 DB에 있는 해쉬랑 비교할 수 있다.
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(principalUserDetailsService).passwordEncoder(encodePassword());
    }

    /**
     * 인증을 무시할 경로를 설정해준다.
     * static 하위 폴더 (css, images, font, video)는 인증없이 접근이 가능해야 한다.
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**", "/images/**", "/font/**", "/video/**");
    }

    /**
     * HttpSecurity를 통해 HTTP 요청에 대한 보안을 구성할 수 있다.
     * csrf().disable() : Spring Secrity에서는 csrf 토큰 없이 요청하면 해당 요청을 막기 때문에 개발 편의성을 위해 비활성화 하였다.
     * authorizeRequest() : HttpServletRequest에 따라 접근을 제한한다.
     * antMachers() : 특정 경로를 지정한다.
     * permitAll(),hasAll() 메소드로 권한에 따른 접근 설정을 한다.
     * .antMatchers("/admin/**").hasRole("ADMIN")
     * /admin으로 시작하는 경로는 ADMIN 권한을 가진 사용자만 접근이 가능하다.
     * .antMatchers("/user/**").hasRole("USER")
     * /user로 시작하는 경로는 USER 권한을 가진 사용자만 접근이 가능하다.
     * .antMatchers("/", "/auth/**","/post/read/**","/posts/search/**").permitAll()
     * 위 경로에 대해서 권한 없이 접근이 가능하다.
     * .anyRequest().authenticated()
     * 그 외의 경로는 인증된 사용자 만이 접근이 가능하다.
     *
     * formLogin() : form 기반으로 인증한다. /login 경로로 접근하면, Spring Security에서 제공하는 로그인 Form을 사용할 수 있다.
     * .loginPage("/auth/login") 인증이 필요할 시 LoginForm으로 인도한다.
     * .loginProcessingUrl : Security에서 해당 주소로 오는 요청을 낚아채서 수행한다.
     * .defaultSuccessUrl("/") : 정상적으로 요청이 완료될시 "/"로 이동한다.
     *
     * logout() : 로그아웃을 지원하는 메소드로 WebSecurityConfigureAdapter를 사용하면 자동으로 적용되나 명시했다.
     * .logoutSuccessUrl("/") 로그아웃 성공시 이동하는 페이지
     * .invalidateHttpSession(true) : Http 세션을 초기화 한다.
     */

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        .csrf().ignoringAntMatchers("/api/**") /* REST API 사용 예외 처리 */
        .and()
        .authorizeRequests()
            .antMatchers("/", "/auth/**", "/admin/join")
            .permitAll()
            .anyRequest()
            .authenticated()
        .and()
            .formLogin()
            .loginPage("/auth/login-form")
            .loginProcessingUrl("/auth/login-proc")
            .failureHandler(authFailureHandler)
            .defaultSuccessUrl("/")
        .and()
            .logout()
            .logoutUrl("/auth/logout-proc")
            .logoutSuccessUrl("/")
            .invalidateHttpSession(true)
        .and() /* OAuth */
            .oauth2Login()
            .userInfoEndpoint()// OAuth2 로그인 성공 후 가져올 설정들
            .userService(customOAuth2UserService); // 서버에서 사용자 정보를 가져온 상태에서 추가로 진행하고자 하는 기능 명시


        http.sessionManagement()
                .maximumSessions(1) //세션 최대 허용 수
                .maxSessionsPreventsLogin(false); // false: 중복 로그인 시 이전 로그인 무효.
    }
}
