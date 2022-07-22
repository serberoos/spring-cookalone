package cookalone.main.service;

import cookalone.main.domain.dto.order.OrderRequestDto;

import java.util.List;

public interface OrderService {
    public Long order(OrderRequestDto orderRequestDto, String email);
}
