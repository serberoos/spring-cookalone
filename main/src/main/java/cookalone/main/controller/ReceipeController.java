package cookalone.main.controller;

import cookalone.main.domain.dto.account.UserDto;
import cookalone.main.domain.dto.receipe.ReceipeDto;
import cookalone.main.service.ReceipeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
@Slf4j
@Controller
@RequiredArgsConstructor
public class ReceipeController {

    private final ReceipeService receipeService;
    private final HttpSession session;

    @GetMapping("/receipe/writeform")
    public String receipeWriteForm(Model model) {
        model.addAttribute("receipeDto", new ReceipeDto.Request());

        return "write_receipe_form";
    }

    @PostMapping("/receipe/create")
    public String createReceipe(@Valid ReceipeDto.Request receipeDto, BindingResult result){
        if (result.hasErrors()) {
            return "write_receipe_form";
        }

        UserDto.Response user = (UserDto.Response) session.getAttribute("user");

        /* 글쓴이 Nickname set */
        receipeDto.setWriter(user.getNickname());

        receipeService.save(receipeDto, user.getNickname());

        return "redirect:/"; //후에 생성된 레시피 페이지로 연결
    }

}
