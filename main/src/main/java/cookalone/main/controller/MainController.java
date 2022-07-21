package cookalone.main.controller;

import cookalone.main.domain.dto.main.MainProductFormDto;
import cookalone.main.domain.dto.search.ContentSearchDto;
import cookalone.main.service.ProductServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

/**
 * IndexController : 메인 페이지 관련
 *
 * @Slf4j
 * Logger log = LoggerFactory.getLogger(getClass());
 */
@Controller
@Slf4j
@RequiredArgsConstructor
public class MainController {

    private final ProductServiceImpl ProductServiceImpl;
    @GetMapping({"/"})
    public String main(ContentSearchDto contentSearchDto, Optional<Integer> page, Model model){
        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 3);
        Page<MainProductFormDto> products = ProductServiceImpl.getMainProductPage(contentSearchDto, pageable);
        model.addAttribute("products", products);
        model.addAttribute("productSearchDto", contentSearchDto);
        model.addAttribute("maxPage", 5);
        return "main";
    }
}
