package cookalone.main.domain.product;

import cookalone.main.domain.ProductCategory;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/* 1. InheritanceType.JOINED: 상속 매핑은 부모 클래스에 @Inheritance 를 사용해야 한다. | 조인 전략 사용
 * 2. DiscriminatorColumn: 부모 클래스에 구분 컬럼을 지정. 이 컬럼으로 저장된 자식 테이블을 구분할 수 있다. 기본값이 DTYPE 이므로 @DiscriminatorColumn 으로 줄여쓸 수 있음.
 * 3. abstract: Product class는 직접 사용하지 않고 자식클래스를 사용하므로 추상클래스로 선언
 * 4. @ManyToMany(mappedBy="productList"): 다대다 관계로 ProductCategory의 productList와 매핑한다.
 *    PRODUCT FK는 ProductCategory 가 가지고 있다.(연관관계의 주인은 ProductCategory)
 */
@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name="DTYPE")
public abstract class Product {
    @Id
    @GeneratedValue
    @Column(name="PRODUCT_ID")
    private Long id;

    @ManyToMany(mappedBy="productList")
    private List<ProductCategory> productCategoryList = new ArrayList<>();

    private String name;
    private int price;
    private int stockQuantity;

}
