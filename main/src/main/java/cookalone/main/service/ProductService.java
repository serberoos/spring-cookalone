package cookalone.main.service;

import cookalone.main.domain.dto.product.MillkitProductRequestDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService{
    public Long saveProduct(MillkitProductRequestDto productRequestDto,
                            List<MultipartFile> itemImgFileList) throws Exception;
}
