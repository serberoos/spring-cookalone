package cookalone.main.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.List;

public class Order {
    @Id
    @GeneratedValue
    private Long id;
    private Delivery user;
    private List<OrderProduct> orderProductList;
    private Delivery delivery;
    private LocalDateTime orderDate;
    private OrderStatus status;
}
