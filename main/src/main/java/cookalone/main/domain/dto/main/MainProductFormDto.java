package cookalone.main.domain.dto.main;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class MainProductFormDto {
    private Long id;
    private String productName;
    private String productDetails;
    private String imgUrl;
    private Integer price;

    @QueryProjection
    public MainProductFormDto(Long id, String productName, String productDetails, String imgUrl, Integer price){
        this.id = id;
        this.productName = productName;
        this.productDetails = productDetails;
        this.imgUrl=imgUrl;
        this.price = price;
    }

}
