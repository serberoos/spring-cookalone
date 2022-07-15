package cookalone.main.domain.dto.product;

import cookalone.main.domain.product.Product;
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
@Builder
public class ProductRequestDto {

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

    public Product toEntity() {
        Product product = Product.builder()
                .id(id)
                .productName(productName)
                .price(price)
                .stockQuantity(stockQuantity)
                .productDetails(productDetails)
                .productSellStatus(productSellStatus)
                .build();
        return product;
    }

}
/**
 * ModelMapper를 쓸 경우 컴파일 에러 대신 예외가 떠서 빌더 패턴으로 구현 하기로 결정함.
 */
//    private static ModelMapper modelMapper = new ModelMapper();
//
//    public Product createProduct(){
//        System.out.println("4");
//        return modelMapper.map(this, Product.class);
//    }
//
//    public static ProductRequestDto of(Product product){
//        System.out.println("2");
//        return modelMapper.map(product, ProductRequestDto.class);
//    }