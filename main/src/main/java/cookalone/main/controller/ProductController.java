package cookalone.main.controller;

import cookalone.main.domain.dto.receipe.ProductRequestDto;
import cookalone.main.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/product/writeform")
    public String productWriteForm(Model model) {
        model.addAttribute("productRequestDto", new ProductRequestDto());

        return "write_product_form";
    }

    @PostMapping("/product/writeform")
    public String productNew(@Valid ProductRequestDto productRequestDto, BindingResult bindingResult,
                             Model model, @RequestParam("productImgFile") List<MultipartFile> productImgFileList) throws Exception {
        if(bindingResult.hasErrors()){
            return "/product/writeform";
        }

        if(productImgFileList.get(0).isEmpty() && productRequestDto.getId() == null){
            model.addAttribute("errorMessage", "첫번째 상품 이미지는 필수 입력 값 입니다.");
            return "/product/writeform";
        }

        try {
            productService.saveProduct(productRequestDto, productImgFileList);
        }catch (Exception e){
            model.addAttribute("errorMessage", "상품 등록 중 에러가 발생하였습니다.");
            return "/product/writeform";
        }

        return "redirect:/";
    }

}
