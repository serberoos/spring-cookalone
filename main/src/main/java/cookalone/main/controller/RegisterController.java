package cookalone.main.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class RegisterController {
    @GetMapping("auth/join")
    public String Register(){
        log.info("Register Controller");
        return "register_one";
    }
}

