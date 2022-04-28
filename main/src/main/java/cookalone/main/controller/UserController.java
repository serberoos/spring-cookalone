package cookalone.main.controller;

import cookalone.main.domain.dto.UserDto;
import cookalone.main.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/user/joinform")
    public String joinForm(){
        log.info("GetMapping joinForm ");
        return "join_form";
    }
    @GetMapping("/user/join")
    public String join(Model model) {
        model.addAttribute("userDto", new UserDto());
        log.info("join Process ");
        return "index";
    }





}

