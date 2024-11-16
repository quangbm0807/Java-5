package org.example.ps27852_lab7.controller;

import jakarta.servlet.http.Cookie;
import lombok.RequiredArgsConstructor;
import org.example.ps27852_lab7.Service.AccountService;
import org.example.ps27852_lab7.Service.ItemService;
import org.example.ps27852_lab7.entity.Account;
import org.example.ps27852_lab7.unti.CookieService;
import org.example.ps27852_lab7.unti.SessionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class Login {
    private final AccountService accountService;
    private final ItemService itemService;
    private final CookieService cookieService;
    private final SessionService sessionService;


    @GetMapping("/login")
    public String login(Model model) {
        Account account = new Account();

        // Đọc thông tin từ cookie
        Cookie usernameCookie = cookieService.get("username");
        Cookie passwordCookie = cookieService.get("password");
        Cookie rememberMeCookie = cookieService.get("rememberMe");

        if (usernameCookie != null) {
            account.setUsername(usernameCookie.getValue());
            System.out.println("Username lay ra la: " + usernameCookie.getValue());
        } else {
            System.out.println("Username cookie is null");
        }

        if (passwordCookie != null) {
            account.setPassword(passwordCookie.getValue());
        } else {
            System.out.println("Password cookie is null");
        }

        if (rememberMeCookie != null) {
            System.out.println("Remember me cookie: " + rememberMeCookie.getValue());
        } else {
            System.out.println("Remember me cookie is null");
        }

        // Thêm đối tượng account vào model để sử dụng trong view
        model.addAttribute("account", account);
        model.addAttribute("rememberMe", rememberMeCookie != null ? rememberMeCookie.getValue() : "false");

        return "login"; // Trả về view login
    }


    @PostMapping("/loginForm")
    public String login(@ModelAttribute Account account, Model model, @RequestParam(required = false) Boolean rememberMe) {
        // Kiểm tra thông tin đăng nhập
        Account loggedInAccount = accountService.login(account.getUsername(), account.getPassword());

        if (loggedInAccount == null) {
            model.addAttribute("message", "Invalid username or password!");
            return "login";
        }

        // Kiểm tra xem tài khoản có đang hoạt động không
        if (!loggedInAccount.isActivated()) {
            model.addAttribute("message", "Account is not activated!");
            return "login"; // Trả về trang đăng nhập kèm theo thông báo
        }

        // Lưu thông tin người dùng vào session nếu tài khoản còn hoạt động
        sessionService.set("loggedInUser", loggedInAccount);

        // Xử lý rememberMe
        if (rememberMe != null && rememberMe) {
            cookieService.add("username", loggedInAccount.getUsername(), 7);
            cookieService.add("password", loggedInAccount.getPassword(), 7);
            cookieService.add("rememberMe", "true", 7);

            System.out.println("Username cookie added: " + loggedInAccount.getUsername());
        } else {
            cookieService.remove("username"); // Xóa cookie tên người dùng nếu không nhớ
            cookieService.remove("password"); // Xóa cookie mật khẩu nếu không nhớ
            cookieService.remove("rememberMe"); // Xóa cookie "Ghi nhớ"
        }
        // Điều hướng người dùng dựa vào vai trò
        if (loggedInAccount.isAdmin()) {
            return "redirect:/home"; // Nếu là admin, chuyển hướng đến trang admin
        } else {
            return "redirect:/user/home"; // Nếu không, chuyển hướng đến trang người dùng
        }
    }

    @GetMapping("/logout")
    public String logout() {
        // Xóa session "loggedInUser"
        itemService.clearCart();
        sessionService.remove("loggedInUser");
        return "redirect:/login";
    }

}
