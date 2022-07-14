package cookalone.main.service;

import cookalone.main.domain.dto.product.MillkitProductRequestDto;
import cookalone.main.domain.dto.product.MillkitProductResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService{
    public Long saveProduct(MillkitProductRequestDto productRequestDto,
                            List<MultipartFile> itemImgFileList) throws Exception;

    public MillkitProductResponseDto getProductDetail(Long productId);

    public Long updateProduct(MillkitProductRequestDto millkitProductRequestDto,
                              List<MultipartFile> productImgFileList) throws Exception;
}
