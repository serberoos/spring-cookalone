//package cookalone.main.controller;
//
//import cookalone.main.domain.dto.account.UserDto;
//import cookalone.main.domain.dto.receipe.ReceipeDto;
//import cookalone.main.service.ReceipeService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import javax.servlet.http.HttpSession;
//
///**
// * @RestController : Rest API Controller
// */
//@RestController
//@RequestMapping(value="/api")
//@RequiredArgsConstructor
//public class ReceipeApiController {
//    private final ReceipeService receipeService;
//    private final HttpSession session;
//
//    /* 레시피 생성 */
//    @PostMapping("/receipe")
//    public ResponseEntity save(ReceipeDto.Request receipeDto){
//
//        UserDto.Response user = (UserDto.Response) session.getAttribute("user");
//
//        return ResponseEntity.ok(receipeService.save(receipeDto, user.getNickname()));
//    }
//
//
//  후에 Json 방식으로 전달해줄 필요가 있다면 사용할 예정
//}
