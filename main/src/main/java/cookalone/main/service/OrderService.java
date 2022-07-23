package cookalone.main.service;

import cookalone.main.domain.Order;
import cookalone.main.domain.dto.order.OrderHistoryDto;
import cookalone.main.domain.dto.order.OrderRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface OrderService {
    public Long order(OrderRequestDto orderRequestDto, String nickname);
    public Page<OrderHistoryDto> getOrderList(String nickname, Pageable pageable);
    public boolean validateOrder(Long orderId, String nickname);
    public void cancelOrder(Long orderId);
}
