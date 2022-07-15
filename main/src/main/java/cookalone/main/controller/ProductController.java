package cookalone.main.controller;

import cookalone.main.domain.dto.product.ProductRequestDto;
import cookalone.main.domain.dto.product.ProductResponseDto;
import cookalone.main.service.ProductServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ProductController {
    private final ProductServiceImpl productServiceImpl;

    @GetMapping("/product/write")
    public String productWriteForm(Model model) {
        model.addAttribute("productRequestDto", new ProductRequestDto());

        return "write_product_form";
    }

    @PostMapping("/product/write")
    public String productWriteProc(@Valid ProductRequestDto productRequestDto, BindingResult bindingResult,
                                   Model model, @RequestParam("productImgFile") List<MultipartFile> productImgFileList) throws Exception {

        if (bindingResult.hasErrors()) {
            return "write_product_form";
        }

        if (productImgFileList.get(0).isEmpty() && productRequestDto.getId() == null) {
            model.addAttribute("errorMessage", "첫번째 상품 이미지는 필수 입력 값 입니다.");

            return "write_product_form";
        }

        try {
            productServiceImpl.saveProduct(productRequestDto, productImgFileList);
        } catch (Exception e) {
            model.addAttribute("errorMessage", "상품 등록 중 에러가 발생하였습니다.");

            return "write_product_form";
        }

        return "redirect:/";
    }

    @GetMapping("/product/update/{id}")
    public String productUpdateForm(@PathVariable Long id, Model model) {
        try {
            ProductResponseDto productResponseDto = productServiceImpl.getProductDetail(id);


            model.addAttribute("productRequestDto", productResponseDto);

        } catch (EntityNotFoundException e) {
            model.addAttribute("errorMessage", "존재하지 않는 상품입니다.");
            model.addAttribute("productRequestDto", new ProductRequestDto());

            return "write_product_form";
        }

        return "update_product_form";
    }

    @PostMapping("/product/update/{id}")
    public String productUpdateProc(@Valid ProductRequestDto productRequestDto,
                                    BindingResult bindingResult, @RequestParam("productImgFile") List<MultipartFile>
                                                productImgFileList, Model model) {
        if(bindingResult.hasErrors()){
            return "update_product_form";
        }
        if(productImgFileList.get(0).isEmpty() && productRequestDto.getId() == null){
            model.addAttribute("errorMessage", "첫번째 상품 이미지는 필수 입력 값입니다.");
            return "update_product_form";
        }

        try {
            productServiceImpl.updateProduct(productRequestDto, productImgFileList);
        } catch (Exception e){
            model.addAttribute("errorMessage", "상품 수정 중 에러가 발생하였습니다.");
            return "update_product_form";
        }

        return "redirect:/";
    }
}
