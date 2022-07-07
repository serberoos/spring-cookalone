package cookalone.main.service;

import cookalone.main.domain.dto.receipe.ProductRequestDto;
import org.springframework.web.multipart.MultipartFile;

import java.nio.channels.MulticastChannel;
import java.util.List;

public interface ProductService {
    public Long saveProduct(ProductRequestDto productRequestDto,
                            List<MultipartFile> itemImgFileList) throws Exception;
}
