package cookalone.main.controller;

import cookalone.main.domain.Receipe;
import cookalone.main.domain.dto.account.UserResponseDto;
import cookalone.main.domain.dto.receipe.ReceipeRequestDto;
import cookalone.main.domain.dto.receipe.ReceipeResponseDto;
import cookalone.main.service.ReceipeServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

/**
 * @PathVariable : URL에 변수를 넣고자 할때 사용
 */
@Slf4j
@Controller
@RequiredArgsConstructor
public class ReceipeController {

    private final ReceipeServiceImpl receipeService;
    private final HttpSession session;

    @GetMapping("/receipe/writeform")
    public String receipeWriteForm(Model model) {
        model.addAttribute("receipeRequestDto", new ReceipeRequestDto());
        return "write_receipe_form";
    }

    /* PageableDefault page = 0, size = 10 */
    @GetMapping("/receipe/searchform")
    public String receipeSearchForm(Model model, @PageableDefault(size=12, sort="id", direction = Sort.Direction.DESC)
            Pageable pageable){
        UserResponseDto user = (UserResponseDto) session.getAttribute("user");
        Page<Receipe> receipes = receipeService.getReceipePages(pageable);
        System.out.println(receipes);

        if (user != null){
            model.addAttribute("user", user);
        }

        model.addAttribute("receipes", receipes);
        model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());
        model.addAttribute("next", pageable.next().getPageNumber());
        model.addAttribute("hasNext", receipes.hasNext());
        model.addAttribute("hasPrev", receipes.hasPrevious());

        return "search_receipe_form";
    }
    @GetMapping("/receipe/{id}")
    public String receipeDetailsForm(@PathVariable Long id, Model model){
        ReceipeResponseDto receipeResponseDto = receipeService.getReceipeDetail(id);

        model.addAttribute("receipeResponseDto", receipeResponseDto);
        return "receipe_details_form";

    }

    @PostMapping("/receipe/create")
    public String createReceipe(@Valid ReceipeRequestDto receipeDto, BindingResult result){
        if (result.hasErrors()) {
            return "write_receipe_form";
        }

        UserResponseDto user = (UserResponseDto) session.getAttribute("user");

        /* 글쓴이 Nickname set */
        receipeDto.setWriter(user.getNickname()); // 후에 Set 외 방식으로 구현 예정

        receipeService.saveReceipe(receipeDto, user.getNickname());

        return "redirect:/"; //후에 생성된 레시피 페이지로 연결
    }
    @GetMapping("/receipe/search")
    public String search(String keyword, Model model){
        List<Receipe> searchList = receipeService.searchReceipe(keyword);

        model.addAttribute("searchList", searchList);

        return "search_receipe_form";
    }
}
