package cookalone.main.domain.dto.product;

import lombok.Setter;
import lombok.Getter;

@Getter @Setter
public class ProductImgRequestDto {

    private Long id;

    private String imgName;

    private String oriImgName;

    private String imgUrl;

    private String repimgYn;

}
