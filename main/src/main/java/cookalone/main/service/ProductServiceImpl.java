package cookalone.main.service;

import cookalone.main.domain.ProductImg;
import cookalone.main.domain.dto.product.MillkitProductRequestDto;
import cookalone.main.domain.product.Product;
import cookalone.main.repository.ProductImgRepository;
import cookalone.main.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

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
    public Long saveProduct(MillkitProductRequestDto millkitProductRequestDto, List<MultipartFile> productImgFileList) throws Exception {

        // 상품 등록

        Product product = millkitProductRequestDto.toEntity();
        productRepository.save(product);

        // 이미지 등록
        for(int i=0; i< productImgFileList.size(); i++){
            System.out.println(productImgFileList.size() + ": productImgFileList.size()");

            ProductImg productImg = new ProductImg();
            productImg.setProduct(product);
            if(i == 0){
                productImg.setRepimgYn("Y");
            } else {
                productImg.setRepimgYn("N");
            }
            productImgService.saveProductImg(productImg, productImgFileList.get(i));
        }
        return product.getId();
    }
}
