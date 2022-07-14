package cookalone.main.domain.dto.product;

import cookalone.main.domain.ProductImg;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class ProductImgResponseDto implements Serializable {
    private Long id;

    private String imgName;

    private String oriImgName;

    private String imgUrl;

    private String repimgYn;

    /* Entity -> Dto */
    public ProductImgResponseDto(ProductImg productImg){
        this.id = productImg.getId();
        this.imgName = productImg.getImgName();
        this.oriImgName = productImg.getOriImgName();
        this.imgUrl = productImg.getImgUrl();
        this.repimgYn = productImg.getRepimgYn();
    }
}
