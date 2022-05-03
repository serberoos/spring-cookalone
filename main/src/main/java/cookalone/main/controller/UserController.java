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
 * UserController : User 회원가입 로그인 | 회원가입 폼 관련
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

    @GetMapping("/user/jointerms-form")
    public String joinTermsFrom(Model model) {
        log.info("GetMapping /user/terms ");
        model.addAttribute("termsDto", new TermsDto());

        return "join_terms";
    }

    @PostMapping("/user/join-form")
    public String joinForm(Model model, @Valid TermsDto termsDto, BindingResult result) {
        log.info("GetMapping joinForm");

        if (result.hasErrors()) {
            return "join_terms";
        }

        model.addAttribute("userDto", new UserDto());

        return "join_form";
    }

    @PostMapping("/user/join-proc")
    public String joinProc(@Valid UserDto userDto, BindingResult result) {
        log.info("PostMapping joinProc");

        if (result.hasErrors()) {
            return "join_form";
        }

//        User user = new User(userDto.getEmail(), userDto.getPassword(), userDto.getNickname(), userDto.getUsername(),
//                userDto.getBirthDate(), userDto.getGender(),
//                new Address(userDto.getCity(), userDto.getStreet(), userDto.getZipcode()));

        userService.join(userDto);

        return "redirect:/";
    }


}

