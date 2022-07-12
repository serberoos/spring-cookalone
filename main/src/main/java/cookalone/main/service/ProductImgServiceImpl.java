package cookalone.main.service;

import cookalone.main.domain.ProductImg;
import cookalone.main.repository.ProductImgRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;


@Service
@RequiredArgsConstructor
@Transactional
public class ProductImgServiceImpl implements ProductImgService{
    @Value("${path_value.productImgLocation}")
    private String productImgLocation;

    private final ProductImgRepository productImgRepository;

    private final FileServiceImpl fileService;




    @Override
    public void saveProductImg(ProductImg productImg, MultipartFile productImgFile) throws Exception {
        String oriImgName = productImgFile.getOriginalFilename();
        String imgName = "";
        String imgUrl = "";

        // 파일 업로드
        if (!StringUtils.isEmpty(oriImgName)){
            imgName = fileService.uploadFile(productImgLocation, oriImgName, productImgFile.getBytes());
            imgUrl = "/images/product/" + imgName;
        }
        //상품 이미지 정보 저장
        productImg.updateProductImg(oriImgName, imgName, imgUrl);
        productImgRepository.save(productImg);
    }
}
