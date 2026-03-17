package vn.com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import vn.com.dto.AuthResponse;
import vn.com.dto.ErrorResponse;
import vn.com.dto.LoginRequest;
import vn.com.entity.User;
import vn.com.repository.UserRepository;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*") // Tạm thời mở all (hoặc để "http://localhost:5173" nếu dùng Vite)
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        
        // 1. Kiểm tra username có tồn tại không
        Optional<User> userOptional = userRepository.findByUsername(loginRequest.getUsername());
        
        // 2. Gộp chung báo lỗi để tăng bảo mật (chống dò quét tài khoản)
        if (userOptional.isEmpty() || !userOptional.get().getPassword().equals(loginRequest.getPassword())) {
            // Trả về 401 Unauthorized thay vì 400 Bad Request
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(new ErrorResponse("Tên đăng nhập hoặc mật khẩu không chính xác!"));
        }

        // 3. Kiểm tra trạng thái tài khoản (ví dụ: bị khóa)
        User user = userOptional.get();
        if (!user.getIsActive()) {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN) // 403 Forbidden
                    .body(new ErrorResponse("Tài khoản của bạn đã bị khóa!"));
        }

        // 4. Đăng nhập thành công -> Trả về DTO (Giấu password đi)
        AuthResponse response = new AuthResponse(
                user.getId(),
                user.getUsername(),
                user.getFullName(),
                user.getRole()
        );

        return ResponseEntity.ok(response); // Trả về 200 OK cùng dữ liệu user
    }
}