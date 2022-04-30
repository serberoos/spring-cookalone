package cookalone.main.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * IndexController : 메인 페이지 관련
 *
 * @Slf4j
 * Logger log = LoggerFactory.getLogger(getClass());
 */
@Controller
@Slf4j
public class IndexController {
    @GetMapping({"","/"})
    public String index(){
        log.info("Index Contoller");
        return "index";
    }
}
