package cookalone.main.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class OrderProduct {
    @Id
    @GeneratedValue
    private Long id;
    private Product product;
    private Order order;
    private int orderPrice;
    private int count;

}
