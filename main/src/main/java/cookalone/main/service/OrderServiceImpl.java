package cookalone.main.service;

import cookalone.main.domain.Member;
import cookalone.main.domain.Order;
import cookalone.main.domain.OrderProduct;
import cookalone.main.domain.ProductImg;
import cookalone.main.domain.dto.order.OrderHistoryDto;
import cookalone.main.domain.dto.order.OrderProductDto;
import cookalone.main.domain.dto.order.OrderRequestDto;
import cookalone.main.domain.product.Product;
import cookalone.main.repository.MemberRepository;
import cookalone.main.repository.OrderRepository;
import cookalone.main.repository.ProductImgRepository;
import cookalone.main.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final ProductRepository productRepository;
    private final MemberRepository memberRepository;
    private final OrderRepository orderRepository;
    private final ProductImgRepository productImgRepository;

    @Override
    @Transactional
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

    @Override
    @Transactional(readOnly = true)
    public Page<OrderHistoryDto> getOrderList(String nickname, Pageable pageable) {
        List<Order> orderList = orderRepository.findOrders(nickname, pageable);
        Long totalCount = orderRepository.countOrder(nickname);

        List<OrderHistoryDto> orderHistoryDtoList = new ArrayList<>();

        for(Order order : orderList){
            OrderHistoryDto orderHistoryDto = new OrderHistoryDto(order);
            System.out.println("@" + orderHistoryDto.getOrderId()
                    +"@"+ orderHistoryDto.getOrderDate()
                    +"@"+ orderHistoryDto.getOrderStatus()
                    );
            List<OrderProduct> orderProductList = order.getOrderProductList();
            for (OrderProduct orderProduct : orderProductList){
                System.out.println("@" + orderProduct.getId()
                        +"@"+ orderProduct.getOrderPrice()
                        +"@"+ orderProduct.getTotalPrice()
                );
                ProductImg productImg = productImgRepository.findByProductIdAndRepimgYn(orderProduct.getProduct().getId(),"Y");
                OrderProductDto orderProductDto = new OrderProductDto(orderProduct, productImg.getImgUrl());
                orderHistoryDto.addOrderProductDto(orderProductDto);
            }

            orderHistoryDtoList.add(orderHistoryDto);
        }
        return new PageImpl<OrderHistoryDto>(orderHistoryDtoList, pageable, totalCount);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean validateOrder(Long orderId, String nickname) {
        Member curMember = memberRepository.findByNickname(nickname);
        Order order = orderRepository.findById(orderId).orElseThrow(() ->
                new IllegalArgumentException("주문이 존재하지 않습니다. id:" + orderId));
        Member savedMember = order.getMember();

        if(!StringUtils.equals(curMember.getNickname(), savedMember.getNickname())){
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public void cancelOrder(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() ->
                new IllegalArgumentException("주문이 존재하지 않습니다. id:" + orderId));
        order.cancelOrder();

    }
}
