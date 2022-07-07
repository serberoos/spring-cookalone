package cookalone.main.controller;

import cookalone.main.domain.dto.receipe.ProductRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ProductController {

    @GetMapping("/product/writeform")
    public String productWriteForm(Model model){
        model.addAttribute("productRequestDto", new ProductRequestDto());

        return "write_product_form";
    }

}
