package cookalone.main.controller;

import cookalone.main.domain.dto.order.OrderHistoryDto;
import cookalone.main.domain.dto.order.OrderRequestDto;
import cookalone.main.service.OrderServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class OrderController {
    private final OrderServiceImpl orderServiceImpl;

    /* 새로고침 없이 서버에 주문을 요청하기 위해 비동기 방식을 사용한다. */
    @PostMapping("/api/order")
    public @ResponseBody ResponseEntity order(@RequestBody @Valid OrderRequestDto orderRequestDto, BindingResult bindingResult, Principal principal) {
        if(bindingResult.hasErrors()){
            StringBuilder sb = new StringBuilder();
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            for (FieldError fieldError: fieldErrors) {
                sb.append(fieldError.getDefaultMessage());
            }
            return new ResponseEntity<String>(sb.toString(), HttpStatus.BAD_REQUEST);
        }

        String nickname = principal.getName();
        Long orderId;
        try {
            orderId = orderServiceImpl.order(orderRequestDto, nickname);
        } catch(Exception e){
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Long>(orderId, HttpStatus.OK);
    }

    @GetMapping({"/product/orders","/product/orders/{page}"})
    public String orderHistory(@PathVariable("page") Optional<Integer> page, Principal principal, Model model){
        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 4);

        Page<OrderHistoryDto> orderHistoryDtoList = orderServiceImpl.getOrderList(principal.getName(), pageable);
        model.addAttribute("orderHistoryDtoList", orderHistoryDtoList);
        model.addAttribute("page", pageable. getPageNumber());
        model.addAttribute("maxPage", 5);

        return "mypage-form";
    }
    @PostMapping("/api/product/order/{orderId}/cancel")
    public @ResponseBody ResponseEntity cancelOrder (@PathVariable("orderId") Long orderId, Principal principal){
        if(!orderServiceImpl.validateOrder(orderId, principal.getName())){
            return new ResponseEntity<String>("주문 취소 권한이 없습니다.", HttpStatus.FORBIDDEN);
        }
        orderServiceImpl.cancelOrder(orderId);
        return new ResponseEntity<Long>(orderId, HttpStatus.OK);
    }
}
