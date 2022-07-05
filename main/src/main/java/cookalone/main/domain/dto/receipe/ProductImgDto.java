package cookalone.main.domain.dto.receipe;

import lombok.Setter;
import lombok.Getter;
import org.modelmapper.ModelMapper;

@Getter @Setter
public class ProductImgDto {

    private Long id;

    private String imgName;

    private String oriImgName;

    private String imgUrl;

    private String repimgYn;

}
