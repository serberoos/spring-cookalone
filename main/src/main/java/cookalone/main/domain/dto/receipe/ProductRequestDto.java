package cookalone.main.domain.dto.receipe;

import cookalone.main.domain.product.Product;
import cookalone.main.domain.status.ProductSellStatus;
import lombok.Setter;
import lombok.Getter;
import org.modelmapper.ModelMapper;

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

    @NotNull(message = "상품 재고를 입력하세요.")
    private Integer stockQuantity;

    @NotBlank(message = "상품 설명을 입력하세요.")
    private String productDetails;

    private ProductSellStatus productSellStatus;

    private List<ProductImgDto> productImgDtoList = new ArrayList<>(); // 상품 저장 후 수정시 상품 이미지 정보를 저장

    private List<Long> productImgIds = new ArrayList<>(); // 상품 이미지 아이디 리스트

    private static ModelMapper modelMapper = new ModelMapper();

    public Product createItem(){
        return modelMapper.map(this, Product.class);
    }

    public static ProductRequestDto of(Product product){
        return modelMapper.map(product, ProductRequestDto.class);
    }


}
