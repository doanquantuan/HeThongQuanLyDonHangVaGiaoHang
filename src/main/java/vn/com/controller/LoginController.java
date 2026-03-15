package vn.com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String showLoginPage() {
        return "auth/login"; 
    }
    @PostMapping("/login")
    public String processLogin(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            Model model) {
        boolean isValidUser = username.equals("admin") && password.equals("123456");

        if (isValidUser) {
            // Đăng nhập thành công -> Chuyển hướng sang trang chủ (ví dụ: /home)
            return "redirect:/home"; 
        } else {
            model.addAttribute("errorMessage", "Tên đăng nhập hoặc mật khẩu không đúng!");
            return "login";
        }
    }
}