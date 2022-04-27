package cookalone.main.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Slf4j
 * Logger log = LoggerFactory.getLogger(getClass());
 */
@Controller
@Slf4j
public class IndexController {
    @GetMapping({"","/"})
    public String Index(){
        log.info("Index Contoller");
        return "index";
    }

    @GetMapping("/user/joinForm")
    public String joinForm(){
        log.info("GetMapping JoinForm ");
        return "register_one";
    }
}
