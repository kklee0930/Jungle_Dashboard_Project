package jungle_week13.jungle_week13.dashboard.presentation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Slf4j
public class PathController {

    @GetMapping("/login")
    public String login() {
        log.info("login page requested");
        return "login";
    }

    @GetMapping("/logout")
    public String logout() {
        log.info("logout page requested");
        return "logout";
    }

    @GetMapping("/signup")
    public String signup() {
        log.info("signup page requested");
        return "signup";
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        log.info("dashboard page requested");
        return "board_list";
    }

    @GetMapping("/board-write")
    public String writePost() {
        log.info("write post page requested");
        return "board_write";
    }

    @GetMapping("/board_read/{id}")
    public String detailPost(Model model, @PathVariable Long id) {
        log.info("detail post page requested");
        model.addAttribute("postId", id);
        return "board_read";
    }
}