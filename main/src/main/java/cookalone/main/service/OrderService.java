package cookalone.main.service;

import cookalone.main.domain.dto.order.OrderHistoryDto;
import cookalone.main.domain.dto.order.OrderRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface OrderService {
    public Long order(OrderRequestDto orderRequestDto, String email);
    public Page<OrderHistoryDto> getOrderList(String nickname, Pageable pageable);
}
