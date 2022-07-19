package cookalone.main.controller;

import cookalone.main.domain.dto.search.ProductSearchDto;
import cookalone.main.domain.product.Product;
import cookalone.main.service.ProductServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class ContentSearchController {
    private final ProductServiceImpl productServiceImpl;
    
    @GetMapping({"/contents", "/contents/{page}"})
    public String contentManage(ProductSearchDto productSearchDto, @PathVariable("page") Optional<Integer> page, Model model){
        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0,3);
        Page<Product> products = productServiceImpl.getAdminProductPage(productSearchDto, pageable);
        model.addAttribute("products",products);
        model.addAttribute("productSearchDto", productSearchDto);
        model.addAttribute("maxPage", 5);
        return "search_content_form";
    }
}
