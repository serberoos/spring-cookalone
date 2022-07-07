package cookalone.main.service;

import cookalone.main.domain.ProductImg;
import org.springframework.web.multipart.MultipartFile;

public interface ProductImgService {
    public void saveProductImg(ProductImg productImg, MultipartFile productImgFile);
}
