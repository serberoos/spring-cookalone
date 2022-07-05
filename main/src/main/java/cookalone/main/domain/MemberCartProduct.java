package cookalone.main.domain;

import cookalone.main.domain.product.Product;
import lombok.Setter;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter @Setter
@Table(name="MEMBER_CART_PRODUCT")
public class MemberCartProduct {
    @Id
    @GeneratedValue
    @Column(name = "MEMBER_CART_PRODUCT_ID")
    private Long id;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="MEMBER_CART_ID")
    private MemberCart memberCart;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="PRODUCT_ID")
    private Product product;

    private int productCount;
}
