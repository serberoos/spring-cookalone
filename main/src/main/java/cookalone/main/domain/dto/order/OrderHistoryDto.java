package cookalone.main.domain.dto.order;

import cookalone.main.domain.Order;
import cookalone.main.domain.status.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * 주문 정보 DTO
 */
@Getter
@Setter
public class OrderHistoryDto {
    private Long orderId;

    private String orderDate;

    private OrderStatus orderStatus;

    private List<OrderProductDto> orderProductDtoList = new ArrayList<>();

    public void addOrderProductDto(OrderProductDto orderProductDto){
        orderProductDtoList.add(orderProductDto);
    }
    public OrderHistoryDto(Order order){
        this.orderId = order.getId();
        this.orderDate = order.getOrderDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        this.orderStatus = order.getOrderStatus();
    }
}
