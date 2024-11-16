package org.example.ps27852_lab7.controller.User;

import lombok.RequiredArgsConstructor;
import org.example.ps27852_lab7.entity.Account;
import org.example.ps27852_lab7.unti.SessionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor

public class HomeUserController {

    private final SessionService sessionService;

    @GetMapping("/home")
    public String home(Model model) {
        Account account = sessionService.get("loggedInUser");
        model.addAttribute("account", account);
        return "user/trangchu";
    }
}
