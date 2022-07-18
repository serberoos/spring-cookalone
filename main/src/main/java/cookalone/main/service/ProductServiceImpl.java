package cookalone.main.service;

import cookalone.main.domain.ProductImg;
import cookalone.main.domain.dto.product.ProductRequestDto;
import cookalone.main.domain.dto.product.ProductResponseDto;
import cookalone.main.domain.dto.product.ProductImgResponseDto;
import cookalone.main.domain.product.Product;
import cookalone.main.repository.ProductImgRepository;
import cookalone.main.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductImgServiceImpl productImgService;
    private final ProductImgRepository productImgRepository;

    @Override
    @Transactional
    public Long saveProduct(ProductRequestDto productRequestDto, List<MultipartFile> productImgFileList) throws Exception {

        // 상품 등록

        Product product = productRequestDto.toEntity();
        productRepository.save(product);

        // 이미지 등록
        for (int i = 0; i < productImgFileList.size(); i++) {


            ProductImg productImg = new ProductImg();
            productImg.setProduct(product);
            if (i == 0) {
                productImg.setRepimgYn("Y");
            } else {
                productImg.setRepimgYn("N");
            }
            productImgService.saveProductImg(productImg, productImgFileList.get(i));
        }
        return product.getId();
    }

    @Transactional(readOnly = true)
    public ProductResponseDto getProductDetails(Long productId) {
        List<ProductImg> productImgList = productImgRepository.findByProductIdOrderByIdAsc(productId);
        List<ProductImgResponseDto> productImgResponseDtoList = new ArrayList<>();
//        List<Long> productImgIdList = new ArrayList<>();

        for (ProductImg productImg : productImgList) {
            ProductImgResponseDto productImgResponseDto = new ProductImgResponseDto(productImg);
            productImgResponseDtoList.add(productImgResponseDto);
//            productImgIdList.add(productImg.getId());
//            System.out.println("productImg id :"+ productImgIdList.getId());
        }

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("상품이 존재하지 않습니다. id:" + productId));
        // ProductImgDto를 Dto에 추가해야 하는가? 후에 다듬기

        return new ProductResponseDto(product, productImgResponseDtoList);
    }

//    @Transactional(readOnly = true)
//    public ProductResponseDto getProductDetailsForUpdateForm(Long productId) {
//        List<ProductImg> productImgList = productImgRepository.findByProductIdOrderByIdAsc(productId);
//        List<ProductImgResponseDto> productImgResponseDtoList = new ArrayList<>();
//
//        for (ProductImg productImg : productImgList) {
//            ProductImgResponseDto productImgResponseDto = new ProductImgResponseDto(productImg);
//            productImgResponseDtoList.add(productImgResponseDto);
//        }
//
//        Product product = productRepository.findById(productId).orElseThrow(() ->
//                new IllegalArgumentException("상품이 존재하지 않습니다. id:" + productId));
//        // ProductImgDto를 Dto에 추가해야 하는가? 후에 다듬기
//
//        return new ProductResponseDto(product, productImgResponseDtoList);
//    }

    @Transactional
    public Long updateProduct(ProductResponseDto productResponseDto,
                              List<MultipartFile> productImgFileList) throws Exception {

        System.out.println("modify product");

        // 상품 수정
        Product product = productRepository.findById(productResponseDto.getId())
                .orElseThrow(() -> new IllegalArgumentException("상품이 존재하지 않습니다."));

        product.updateProduct(productResponseDto);

        List<Long> productImgIdList = productResponseDto.getProductImgIdList();
        System.out.println("전체출력: " + productImgIdList.toString());
        //이미지 등록
        for(int i = 0; i < productImgFileList.size(); i++){
            System.out.println("modify product");
            System.out.println("productImgFileList.size() :" +productImgFileList.size());
            System.out.println("i :" +i);
            System.out.println(productImgFileList.get(i));
            System.out.println("pass1");

            System.out.println(productImgIdList.get(i));
            System.out.println("pass2");

            productImgService.updateProductImg(productImgIdList.get(i), productImgFileList.get(i));
            System.out.println("pass-fin");
        }
        return product.getId();
    }
}
