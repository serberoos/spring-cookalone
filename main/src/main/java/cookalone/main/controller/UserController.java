package cookalone.main.controller;

import cookalone.main.domain.Address;
import cookalone.main.domain.User;
import cookalone.main.domain.dto.UserDto;
import cookalone.main.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

/**
 * UserController : User 회원가입 로그인 | 회원가입 폼 관련
 *
 * model.addAttribute("userDto", new UserDto()); :: joinForm에서 joinProc을 수행하려면 빈 UserDto를 들고 가야함.
 * => 이때 POST에서는 UserDto 객체가 Parameter로 넘어오게 됨.
 *
 * @Valid : javax.validation 기능을 쓴다고 명시한다. | @NotEmpty 등등
 * */
@Controller
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    /* 회원가입 */

    @GetMapping("/user/jointerms-form")
    public String joinTermsFrom(){
        log.info("GetMapping /user/terms ");
        return "join_terms";
    }

    @GetMapping("/user/join-form")
    public String joinForm(Model model){
        log.info("GetMapping joinForm");
        model.addAttribute("userDto", new UserDto());

        return "join_form";
    }

    @PostMapping("/user/join-proc")
    public String joinProc(@Valid UserDto userDto) {
        log.info("PostMapping joinProc");
        User user = new User(userDto.getEmail(), userDto.getPassword(), userDto.getNickname(), userDto.getUsername(),
                userDto.getBirthDate(), userDto.getGender(),
                new Address(userDto.getCity(), userDto.getStreet(), userDto.getZipcode()));

        userService.join(user);

        return "redirect:/";
    }





}

