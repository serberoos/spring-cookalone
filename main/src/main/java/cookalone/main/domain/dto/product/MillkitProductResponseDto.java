package cookalone.main.domain.dto.product;

import cookalone.main.domain.product.Product;
import cookalone.main.domain.status.ProductSellStatus;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class MillkitProductResponseDto implements Serializable {
    private Long id;

    private String productName;

    private Integer price;

    private Integer stockQuantity;

    private String productDetails;

    private ProductSellStatus productSellStatus;

    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    private List<ProductImgResponseDto> productImgResponseDtoList = new ArrayList<>(); // 상품 저장 후 수정시 상품 이미지 정보를 저장

//    private List<Long> productImgIdList = new ArrayList<>(); // 상품 이미지 아이디 리스트

    /* Entity -> Dto */
    public MillkitProductResponseDto(Product product, List<ProductImgResponseDto> productImgResponseDtoList){

        this.id = product.getId();
        this.productName = product.getProductName();
        this.price = product.getPrice();
        this.stockQuantity = product.getStockQuantity();
        this.productDetails = product.getProductDetails();
        this.productSellStatus = product.getProductSellStatus();
        this.createdDate = product.getCreatedDate();
        this.modifiedDate = product.getModifiedDate();
        this.productImgResponseDtoList = productImgResponseDtoList;
//        this.productImgIdList = product.getProductImgIdList();
    }
}
