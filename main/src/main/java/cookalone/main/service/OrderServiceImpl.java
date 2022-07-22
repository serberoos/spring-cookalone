package cookalone.main.service;

import cookalone.main.domain.Member;
import cookalone.main.domain.Order;
import cookalone.main.domain.OrderProduct;
import cookalone.main.domain.dto.order.OrderRequestDto;
import cookalone.main.domain.product.Product;
import cookalone.main.repository.MemberRepository;
import cookalone.main.repository.OrderRepository;
import cookalone.main.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final ProductRepository productRepository;
    private final MemberRepository memberRepository;
    private final OrderRepository orderRepository;

    @Override
    public Long order(OrderRequestDto orderRequestDto, String nickname) {

        Long productId = orderRequestDto.getProductId();
        Product product = productRepository.findById(productId).orElseThrow(() ->
                new IllegalArgumentException("상품이 존재하지 않습니다. id:" + productId));

        Member member = memberRepository.findByNickname(nickname); // 문제 있을 수 있음.

        List<OrderProduct> orderProductList = new ArrayList<>();
        OrderProduct orderProduct = OrderProduct.createOrderProduct(product, orderRequestDto.getOrderCount());
        orderProductList.add(orderProduct);

        Order order = Order.createOrder(member, orderProductList);
        orderRepository.save(order);

        return order.getId();
    }
}
