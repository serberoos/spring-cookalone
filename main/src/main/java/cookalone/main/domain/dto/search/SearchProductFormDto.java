package cookalone.main.domain.dto.search;

import com.querydsl.core.annotations.QueryProjection;
import cookalone.main.domain.status.ProductSellStatus;
import lombok.Data;

@Data
public class SearchProductFormDto {
    private Long id;
    private String productName;
    private String productDetails;
    private String imgUrl;
    private Integer price;
    private ProductSellStatus productSellStatus;
    private String createdBy;


    @QueryProjection
    public SearchProductFormDto(Long id, String productName, String productDetails, String imgUrl, Integer price, ProductSellStatus productSellStatus, String createdBy){
        this.id = id;
        this.productName = productName;
        this.productDetails = productDetails;
        this.imgUrl = imgUrl;
        this.price = price;
        this.productSellStatus = productSellStatus;
        this.createdBy = createdBy;
    }

}

