package cookalone.main.domain.dto.receipe;

import cookalone.main.domain.status.ProductSellStatus;
import lombok.Setter;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class ProductRequestDto {

    private Long id;

    @NotBlank(message = "상품 명을 입력하세요.")
    private String productName;

    @NotNull(message = "가격을 입력하세요.")
    private Integer price;

    @NotBlank(message = "상품 설명을 입력하세요.")
    private String productDetails;

    @NotNull(message = "상품 재고를 입력하세요.")
    private Integer stockQuantity;

    private ProductSellStatus productSellStatus;

    private List<ProductImgDto> productImgDtos = new ArrayList<>(); // 상품 저장 후 수정시 상품 이미지 정보를 저장

    private List<Long> productImgIds = new ArrayList<>(); // 상품 이미지 아이디 리스트




}
