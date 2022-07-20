package cookalone.main.service;

import cookalone.main.domain.ProductImg;
import cookalone.main.domain.dto.product.ProductRequestDto;
import cookalone.main.domain.dto.product.ProductResponseDto;
import cookalone.main.domain.dto.product.ProductImgResponseDto;
import cookalone.main.domain.dto.search.ProductSearchDto;
import cookalone.main.domain.product.Product;
import cookalone.main.repository.ProductImgRepository;
import cookalone.main.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
        System.out.println("2");
        // 상품 등록

        Product product = productRequestDto.toEntity();
        productRepository.save(product);
        System.out.println("3");
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

        for (ProductImg productImg : productImgList) {
            ProductImgResponseDto productImgResponseDto = new ProductImgResponseDto(productImg);
            productImgResponseDtoList.add(productImgResponseDto);

        }

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("상품이 존재하지 않습니다. id:" + productId));

        return new ProductResponseDto(product, productImgResponseDtoList);
    }

    @Transactional
    public Long updateProduct(ProductResponseDto productResponseDto,
                              List<MultipartFile> productImgFileList) throws Exception {

        // 상품 수정
        Product product = productRepository.findById(productResponseDto.getId())
                .orElseThrow(() -> new IllegalArgumentException("상품이 존재하지 않습니다."));

        product.updateProduct(productResponseDto);

        List<Long> productImgIdList = productResponseDto.getProductImgIdList();

        //이미지 등록
        for(int i = 0; i < productImgFileList.size(); i++){
            productImgService.updateProductImg(productImgIdList.get(i), productImgFileList.get(i));
        }
        return product.getId();
    }

    @Transactional(readOnly = true)
    public Page<Product> getAdminProductPage(ProductSearchDto productSearchDto,
                                             Pageable pageable){
        return productRepository.getAdminProductPage(productSearchDto, pageable);
    }
}
