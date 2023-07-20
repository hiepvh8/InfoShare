package com.vuhoanghiep.InfoShare.serviceImp;

import com.vuhoanghiep.InfoShare.Enum.Role;
import com.vuhoanghiep.InfoShare.exception.NotFoundException;
import com.vuhoanghiep.InfoShare.model.dto.AuthResponse;
import com.vuhoanghiep.InfoShare.model.dto.LoginDTO;
import com.vuhoanghiep.InfoShare.model.dto.UserDTO;
import com.vuhoanghiep.InfoShare.model.entity.User;
import com.vuhoanghiep.InfoShare.repository.UserRepository;
import com.vuhoanghiep.InfoShare.security.JwtService;
import com.vuhoanghiep.InfoShare.service.AuthService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImp implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthServiceImp(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public AuthResponse register(UserDTO userDTO) {
        // Kiểm tra xem email đã tồn tại hay chưa
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            // Email đã tồn tại, trả về lỗi
            throw new NotFoundException("Email "+ userDTO.getEmail() + " đã tồn tại trong hệ thống!");
        }else {
            User user = new User();
            user.setUsername(userDTO.getUsername());
            user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            user.setEmail(userDTO.getEmail());
            user.setFullname(userDTO.getFullname());
            user.setRole(Role.USER);
            userRepository.save(user);
            var jwt = jwtService.generateToken(user);
            AuthResponse authResponse = new AuthResponse();
            authResponse.setToken(jwt);
            return authResponse;
        }
    }

    public AuthResponse login(LoginDTO loginDTO) {
        // Kiểm tra xem người dùng có tồn tại không
        Optional<User> user = userRepository.findByEmail(loginDTO.getEmail());
        if (!user.isPresent()) {
            throw new NotFoundException("Người dùng không tồn tại.");
        }else {
            // Kiểm tra tính đúng đắn của mật khẩu
            if (!passwordEncoder.matches(loginDTO.getPassword(), user.get().getPassword())) {
                throw new NotFoundException("Sai tên đăng nhập hoặc mật khẩu.");
            }else {
                // Tạo mã token
                String jwt = jwtService.generateToken(user.get());

                AuthResponse authResponse = new AuthResponse();
                authResponse.setToken(jwt);
                // Có thể thêm thông tin người dùng khác vào đây nếu cần
                return authResponse;
            }
        }
    }
}
