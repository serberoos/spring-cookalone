package cookalone.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReceipeController {

    @GetMapping("/receipe/write")
    public String receipeWriteForm(Model model) {
//        model.addAttribute("receipeDto", new ReceipeDto());

        return "write_receipe_form";
    }
}
