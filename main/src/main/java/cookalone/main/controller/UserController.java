package cookalone.main.controller;

import cookalone.main.domain.Address;
import cookalone.main.domain.User;
import cookalone.main.domain.dto.account.TermsDto;
import cookalone.main.domain.dto.account.UserDto;
import cookalone.main.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

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
 */
@Controller
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    /* 회원가입 */

    @GetMapping("/auth/jointerms-form")
    public String joinTermsForm(Model model) {
        model.addAttribute("termsDto", new TermsDto());

        return "join_terms";
    }

    @PostMapping("/auth/join-form")
    public String joinForm(Model model, @Valid TermsDto termsDto, BindingResult result) {
        model.addAttribute("userDto", new UserDto.Request());
        if (result.hasErrors()) {
            return "join_terms";
        }

        return "join_form";
    }

    @PostMapping("/auth/join-proc")
    public String joinProc(@Valid UserDto.Request userDto, BindingResult result) {
        if (result.hasErrors()) {
            return "join_form";
        }
        userService.join(userDto);


        return "redirect:/";
    }

    /* 로그인 */
    @GetMapping("/auth/login-form")
    public String loginForm() {

        return "login_form";
    }

    @GetMapping("/auth/login-proc")
    public String loginProc(){
        return "redirect:/";
    }


}

