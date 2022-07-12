package cookalone.main.domain.dto.product;

import cookalone.main.domain.product.Millkit;
import cookalone.main.domain.status.ProductSellStatus;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class MillkitProductRequestDto {

    private Long id;

    @NotBlank(message = "상품 명을 입력하세요.")
    private String productName;

    @NotNull(message = "가격을 입력하세요.")
    private Integer price;

    @NotNull(message = "상품 재고를 입력하세요.")
    private Integer stockQuantity;

    @NotBlank(message = "상품 설명을 입력하세요.")
    private String productDetails;

    private ProductSellStatus productSellStatus;

    private String createdDate;
    private String modifiedDate;

    private List<ProductImgRequestDto> productImgRequestDtoList = new ArrayList<>(); // 상품 저장 후 수정시 상품 이미지 정보를 저장

    private List<Long> productImgIdList = new ArrayList<>(); // 상품 이미지 아이디 리스트

    public Millkit toEntity() {
        Millkit millkit = Millkit.builder()
                .id(id)
                .productName(productName)
                .price(price)
                .stockQuantity(stockQuantity)
                .productDetails(productDetails)
                .productSellStatus(productSellStatus)
                .build();
        return millkit;
    }

}
