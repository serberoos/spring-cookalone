package cookalone.main.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Slf4j
 * Logger log = LoggerFactory.getLogger(getClass());
 */
@Controller
@Slf4j
public class IndexController {
    @RequestMapping("/")
    public String Index(){
        log.info("index contoller");
        return "index";
    }
}
