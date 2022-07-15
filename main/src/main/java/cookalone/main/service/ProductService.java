package cookalone.main.service;

import cookalone.main.domain.dto.product.ProductRequestDto;
import cookalone.main.domain.dto.product.ProductResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService{
    public Long saveProduct(ProductRequestDto productRequestDto,
                            List<MultipartFile> itemImgFileList) throws Exception;

    public ProductResponseDto getProductDetails(Long productId);

    public Long updateProduct(ProductRequestDto productRequestDto,
                              List<MultipartFile> productImgFileList) throws Exception;
}
