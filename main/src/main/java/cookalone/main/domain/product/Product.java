package cookalone.main.domain.product;

import cookalone.main.domain.dto.product.ProductRequestDto;
import cookalone.main.domain.dto.product.ProductResponseDto;
import cookalone.main.domain.exception.OutOfStockException;
import cookalone.main.domain.status.ProductSellStatus;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @Lob : Large OBject의 줄임말, 필드에 특정 문자열 길이를 지정하지 않으면 디폴트로 varchar(255)까지 저장 가능하지만 사진 저장시 더 많은 자리수를 사용하기 때문에 @Lob를 사용한다.
 */
/* 1. InheritanceType.JOINED: 상속 매핑은 부모 클래스에 @Inheritance 를 사용해야 한다. | 조인 전략 사용
 * 2. DiscriminatorColumn: 부모 클래스에 구분 컬럼을 지정. 이 컬럼으로 저장된 자식 테이블을 구분할 수 있다. 기본값이 DTYPE 이므로 @DiscriminatorColumn 으로 줄여쓸 수 있음.
 * 3. abstract: Product class는 직접 사용하지 않고 자식클래스를 사용하므로 추상클래스로 선언
 * 4. @ManyToMany(mappedBy="productList"): 다대다 관계로 ProductCategory의 productList와 매핑한다.
 *    PRODUCT FK는 ProductCategory 가 가지고 있다.(연관관계의 주인은 ProductCategory)
 */

@Builder
@ToString
@Entity @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name="product")
@Inheritance(strategy = InheritanceType.JOINED)
@EntityListeners(AuditingEntityListener.class)
@DiscriminatorColumn(name="DTYPE")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="PRODUCT_ID")
    private Long id;

    @Column(nullable = false, length = 50)
    private String productName;

    @Column(name="price", nullable = false)
    private int price;

    @Column(nullable = false)
    private int stockQuantity;

    @Lob
    @Column(nullable = false)
    private String productDetails;

    @Enumerated(EnumType.STRING)
    private ProductSellStatus productSellStatus;
    

//    @Column(name= "created_by", nullable = false)
    @CreatedBy
    private String createdBy;

    @Column(name= "created_date", nullable = false)
    @CreatedDate
    private LocalDateTime createdDate;

    @Column(name= "modified_date", nullable = false)
    @LastModifiedDate
    private LocalDateTime modifiedDate;

    public void updateProduct(ProductResponseDto productResponseDto) {
        this.productName = productResponseDto.getProductName();
        this.price = productResponseDto.getPrice();
        this.stockQuantity = productResponseDto.getStockQuantity();
        this.productDetails = productResponseDto.getProductDetails();
        this.productSellStatus = productResponseDto.getProductSellStatus();
    }


//    @ManyToMany(mappedBy="productList")
//    private List<ProductCategory> productCategoryList = new ArrayList<>();

    public void removeStock(int stockQuantity) {
        int restStock = this.stockQuantity - stockQuantity;

        if (restStock < 0) {
            throw new OutOfStockException("상품의 재고가 부족합니다. 현 재고 수량: " + this.stockQuantity + ")");
        }
        this.stockQuantity = restStock;
    }

    public void addStock(int stockQuantity){
        this.stockQuantity += stockQuantity;
    }
}
