package cookalone.main.controller;

import cookalone.main.domain.dto.account.MemberRequestDto;
import cookalone.main.service.MemberServiceImpl;
import cookalone.main.validator.CheckOverlapUserEmailValidator;
import cookalone.main.validator.CheckOverlapUserNicknameValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.Errors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Map;

/**
 * UserController : User 회원가입 로그인 | 회원가입 폼 관련 @Controller를 붙인다는 것은 View를 리턴하겠다는 뜻이다.
 * <p>
 * model.addAttribute("userDto", new UserDto()); :: joinForm에서 joinProc을 수행하려면 빈 UserDto를 들고 가야함.
 * => 이때 POST에서는 UserDto 객체가 Parameter로 넘어오게 됨.
 *
 * @Valid : javax.validation 기능을 쓴다고 명시한다. | @NotEmpty 등등
 * <p>
 * BindingResult :: 오류가 생기면 해당 객체에 오류가 담겨서 프로세스가 실행이 된다.
 * Spring과 thymeleaf가 integration이 잘 되어 있어서 오류를 join_form 화면까지 끌고 가 준다.
 * if (result.hasErrors()){
 * return "join_form";
 * }
 * //에러 메세지를 html 상 thymeleaf에서 띄운다. (@NotEmpty 메세지가 출력됨.)
 * @InitBinder : 특정 컨트롤러에서 바인딩 또는 검증 설정을 변경하고 싶을 때 사용한다.
 * webDataBinder binder : HTTP 요청 정보를 컨트롤러 메소드의 파라미터나 모델에 바인딩할 떄 사용되는 바인딩 객체
 */
@Slf4j
@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberServiceImpl accountService;
    private final CheckOverlapUserEmailValidator checkOverlapUserEmailValidator;
    private final CheckOverlapUserNicknameValidator checkOverlapUserNicknameValidator;

    /* 커스텀 유효성 검사 validate */
    @InitBinder
    public void validatorBinder(WebDataBinder binder){
        binder.addValidators(checkOverlapUserEmailValidator);
        binder.addValidators(checkOverlapUserNicknameValidator);
    }
//    private final UserRequestDtoValidator userRequestDtoValidator;

//    /**
//     * 컨트롤러 호출 시 마다 WebDataBinder를 통해 Validator를 등록한다.
//     */
//    @InitBinder
//    public void init(WebDataBinder webDataBinder){
//        webDataBinder.addValidators(userRequestDtoValidator);
//    }


    /* 유저 회원가입 폼 이동 */
    @GetMapping("/auth/join")
    public String userJoinForm(MemberRequestDto userDto) {
        return "user_join_form";
    }

    /* 유저 회원가입 프로세스 */
    @PostMapping("/auth/join")
    public String userJoinProc(Model model, @Valid MemberRequestDto memberRequestDto, Errors errors) {
        if (errors.hasErrors()) {
            /* 회원가입 실패시, 입력 데이터를 유지합니다. */
            model.addAttribute("userRequestDto", memberRequestDto);

            /* 유효성을 통과하지 못한 필드와 메세지 핸들링 */
            Map<String, String> validatorResult = accountService.validateHandling(errors);
            for (String key : validatorResult.keySet()) {
                System.out.println((key + " " + validatorResult.get(key)));
                model.addAttribute(key, validatorResult.get(key));
            }
            errors.getAllErrors().forEach(v -> { //에러 출력
                System.out.println(v.toString());
            });

            /* 회원가입 페이지로 다시 리턴 */
            return "user_join_form";
        }
        accountService.join(memberRequestDto);

        return "redirect:/";
    }

    /* 관리자 회원가입 폼 이동 */
    @GetMapping("/admin/join")
    public String adminJoinForm(MemberRequestDto memberRequestDto) {
        return "admin_join_form";
    }

    /* 관리자 계정 생성 프로세스 */
    @PostMapping("/admin/join")
    public String adminJoinProc(Model model, @Valid MemberRequestDto memberRequestDto, Errors errors){
        if (errors.hasErrors()) {
            /* 회원가입 실패시, 입력 데이터를 유지합니다. */
            model.addAttribute("adminRequestDto", memberRequestDto);

            /* 유효성을 통과하지 못한 필드와 메세지 핸들링 */
            Map<String, String> validatorResult = accountService.validateHandling(errors);
            for (String key : validatorResult.keySet()) {
                System.out.println((key + " " + validatorResult.get(key)));
                model.addAttribute(key, validatorResult.get(key));
            }
            errors.getAllErrors().forEach(v -> { //에러 출력
                System.out.println(v.toString());
            });

            /* 관리자 회원가입 페이지로 다시 리턴 */
            return "admin_join_form";
        }
        accountService.join(memberRequestDto);

        return "redirect:/";
    }

    /* 로그인 */
    @GetMapping("/auth/login-form")
    public String loginProc(@RequestParam(value = "error", required = false) String error,
                            @RequestParam(value = "exception", required = false) String exception,
                            Model model) {
        model.addAttribute("error", error);
        model.addAttribute("exception", exception);

        return "login_form";
    }

    /**
     * GET : 컨트롤러에서 매핑을 받고, 로그아웃 기능으로 우회한다.
     */
    /* 로그아웃 */
    @GetMapping("/auth/logout-proc")
    public String logoutProc(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/";
    }
}

