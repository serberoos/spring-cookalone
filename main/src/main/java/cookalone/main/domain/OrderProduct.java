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

    @Column(name= "created_date", nullable = true) // nullable 나중에 리팩토링 할때 false로 바꿀 것
    @CreatedDate
    private LocalDateTime createdDate;

    @Column(name= "modified_date", nullable = true) // nullable 나중에 리팩토링 할때 false로 바꿀 것
    @LastModifiedDate
    private LocalDateTime modifiedDate;
    // 후에 리팩토링
    public static OrderProduct createOrderProduct(Product product, int count){
        OrderProduct orderProduct = new OrderProduct();
        orderProduct.setProduct(product);
        orderProduct.setOrderCount(count);
        orderProduct.setOrderPrice(product.getPrice());

        product.removeStock(count);

        return orderProduct;
    }
    // 후에 리팩토링
    public int getTotalPrice(){
        return orderPrice * orderCount;
    }

    public void cancel() {
        this.getProduct().addStock(orderCount);
    }

}
