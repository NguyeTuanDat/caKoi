package ut.edu.koii.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ut.edu.koii.models.Role;
import ut.edu.koii.models.User;
import ut.edu.koii.models.UserDto;
import ut.edu.koii.repository.RoleRepository;
import ut.edu.koii.repository.UserRepository;

import java.util.Optional;
import java.util.Set;
import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Transactional
    public User createUser(User user) {
        Role role = roleRepository.findById(2L)
                .orElseThrow(() -> new RuntimeException("Vai trò không tồn tại"));
        user.getRoles().add(role);
        return userRepository.save(user);
    }
    // Triển khai đăng ký mới
    @Override
    public boolean registerNewUser(UserDto dto) {
        // 1. Kiểm tra mật khẩu trùng khớp
        if (!dto.getPassword().equals(dto.getConfirmPassword())) {
            return false;
        }
        // 2. Kiểm tra email chưa tồn tại
        if (userRepository.existsByEmail(dto.getEmail())) {
            return false;
        }
        // 3. Tạo entity User và mã hóa mật khẩu
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        // 4. Gán role mặc định ROLE_USER
        var userRole = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("Role USER không tồn tại"));
        user.setRoles(Set.of(userRole));
        userRepository.save(user);
        return true;
    }
}
