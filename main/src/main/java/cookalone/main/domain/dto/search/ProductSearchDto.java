package cookalone.main.domain.dto.search;


import cookalone.main.domain.status.ProductSellStatus;
import lombok.Data;

@Data
public class ProductSearchDto {
    private String searchDateType;

    private ProductSellStatus searchSellStatus;

    private String searchBy;

    private String searchQuery = "";

}
