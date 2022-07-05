package cookalone.main.domain;

import cookalone.main.domain.product.Product;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderProduct {
    @Id
    @GeneratedValue
    @Column(name="ORDER_PRODUCT_ID")
    private Long id;

    /* 1. mappedby : PRODUCT FK는 ORDER_PRODUCT 가 가지고 있다.(연관관계의 주인은 ORDER_PRODUCT)
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

    /* 1. mappedby : ORDER FK는 ORDER_PRODUCT 가 가지고 있다.(연관관계의 주인은 ORDER_PRODUCT)
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORDER_ID")
    private Order order;

    private int orderPrice;
    private int orderCount;

    @Column(name= "created_date", nullable = false)
    @CreatedDate
    private LocalDateTime createdDate;

    @Column(name= "modified_date", nullable = false)
    @LastModifiedDate
    private LocalDateTime modifiedDate;

}
