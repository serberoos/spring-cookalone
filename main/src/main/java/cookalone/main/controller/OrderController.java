package cookalone.main.controller;

import cookalone.main.domain.dto.order.OrderRequestDto;
import cookalone.main.service.OrderService;
import cookalone.main.service.OrderServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {
    private final OrderServiceImpl orderServiceImpl;

    /* 새로고침 없이 서버에 주문을 요청하기 위해 비동기 방식을 사용한다. */
    @PostMapping("/api/order")
    public @ResponseBody ResponseEntity order(@RequestBody @Valid OrderRequestDto orderRequestDto, BindingResult bindingResult, Principal principal) {
        System.out.println(orderRequestDto.getOrderCount() + "@" + orderRequestDto.getProductId());
        if(bindingResult.hasErrors()){
            StringBuilder sb = new StringBuilder();
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            for (FieldError fieldError: fieldErrors) {
                sb.append(fieldError.getDefaultMessage());
            }
            return new ResponseEntity<String>(sb.toString(), HttpStatus.BAD_REQUEST);
        }

        String nickname = principal.getName();
        System.out.println(nickname + "@");
        Long orderId;
        try {
            orderId = orderServiceImpl.order(orderRequestDto, nickname);
        } catch(Exception e){
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Long>(orderId,HttpStatus.OK);
    }
}
