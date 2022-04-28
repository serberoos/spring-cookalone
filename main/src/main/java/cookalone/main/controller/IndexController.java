package cookalone.main.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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

    @GetMapping("/user/terms")
    public String terms(){
        log.info("GetMapping /user/terms ");
        return "join_terms";
    }
}
