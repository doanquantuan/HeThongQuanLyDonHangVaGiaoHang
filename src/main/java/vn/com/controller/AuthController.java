package vn.com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.com.entity.User;
import vn.com.repository.UserRepository;
import vn.com.dto.LoginRequest;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    // API ĐĂNG NHẬP DUY NHẤT CHO ADMIN
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        // Tìm tài khoản trong DB
        Optional<User> userOptional = userRepository.findByUsername(loginRequest.getUsername());
        
        // Gộp chung báo lỗi để tăng bảo mật (không cho người ta biết sai user hay sai pass)
        if (userOptional.isEmpty() || !userOptional.get().getPassword().equals(loginRequest.getPassword())) {
            return ResponseEntity.badRequest().body("Lỗi: Sai tài khoản hoặc mật khẩu!");
        }

        User user = userOptional.get();

        // Đăng nhập thành công -> Trả về thông tin
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Đăng nhập thành công!");
        response.put("userId", user.getId());
        response.put("username", user.getUsername());
        response.put("fullName", user.getFullName());
        response.put("role", user.getRole());

        return ResponseEntity.ok(response);
    }
}