package vn.com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    // Hàm này chỉ có tác dụng duy nhất: Hiển thị giao diện HTML
    @GetMapping("/")
    public String showLoginPage() {
        return "auth/login"; // Trả về file login.html nằm trong thư mục templates/auth/
    }
    
}