package cookalone.main.domain.dto.order;

import cookalone.main.domain.OrderProduct;
import lombok.Getter;
import lombok.Setter;

/**
 * 조회한 주문 데이터를 화면에 보낼 때 사용할 DTO
 */
@Getter
@Setter
public class OrderProductDto {
    private String productName;

    private int orderCount;

    private int orderPrice;

    private String imgUrl;


    public OrderProductDto(OrderProduct orderProduct, String imgUrl) { // 후에 리팩토링
        this.productName = orderProduct.getProduct().getProductName();
        this.orderCount = orderProduct.getOrderCount();
        this.orderPrice = orderProduct.getOrderPrice();
        this.imgUrl = imgUrl;
    }
}
