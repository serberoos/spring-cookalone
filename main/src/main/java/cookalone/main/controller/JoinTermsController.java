package cookalone.main.controller;

import cookalone.main.domain.dto.account.JoinTermsRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Slf4j
@Controller
@RequiredArgsConstructor
public class JoinTermsController {

    /* 유저 회원가입 약관 과정 */
    @GetMapping("/auth/jointerms")
    public String joinTermsForm(Model model) {
        model.addAttribute("joinTermsRequestDto", new JoinTermsRequestDto());

        return "join_terms";
    }

    @PostMapping("/auth/jointerms")
    public String joinTermsProc(Model model, @Valid JoinTermsRequestDto joinTermsRequestDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {

            return "join_terms";
        }
        return "redirect:/auth/join";
    }
}
