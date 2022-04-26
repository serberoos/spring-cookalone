package cookalone.main.domain;

import cookalone.main.domain.product.Product;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="PRODUCT_CATEGORY")
@Getter @Setter
public class ProductCategory {
    @Id
    @GeneratedValue
    @Column(name="PRODUCT_CATEGORY_ID")
    private Long id;

    private String name;

    /* 1. @JoinTable : DB 상으로는 1 : M, N : 1 관계를 풀어내는 조인테이블을 사용하지만
          Item 과 Category 의 외래키 이외의 속성들을 관리하지 않으므로 연결 엔티티를 두지 않고 @JoinTable 만 사용했다.
     */
    @ManyToMany
    @JoinTable(name = "PRODUCT_CATEGORY",
            joinColumns = @JoinColumn(name = "PRODUCT_CATEGORY_ID"),
            inverseJoinColumns = @JoinColumn(name = "PRODUCT_ID"))
    private List<Product> productList = new ArrayList<>();

    /* 1. parent, child : 후에 정리 필요
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PARENT_ID")
    private ProductCategory parent;

    @OneToMany(mappedBy = "parent")
    private List<ProductCategory> child = new ArrayList<>();
}
