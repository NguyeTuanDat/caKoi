package ut.edu.koii.models;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ut.edu.koii.repository.RoleRepository;
import ut.edu.koii.repository.UserRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component
public class UserDataLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserDataLoader(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        // Tìm hoặc tạo các vai trò
        Role adminRole = roleRepository.findByName("ADMIN")
                .orElseGet(() -> roleRepository.save(new Role("ADMIN")));
        Role userRole = roleRepository.findByName("USER")
                .orElseGet(() -> roleRepository.save(new Role("USER")));

        // Tạo tài khoản admin nếu chưa tồn tại
        if (!userRepository.existsByUsername("admin")) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setEnabled(true);
            admin.setEmail("admin@example.com");
            admin.setFullName("Quản trị viên");

            // Tạo và gán vai trò cho tài khoản admin
            Set<Role> adminRoles = new HashSet<>();
            adminRoles.add(adminRole);
            admin.setRoles(adminRoles);

            userRepository.save(admin);
        }

        // Tạo tài khoản user nếu chưa tồn tại
        if (!userRepository.existsByUsername("user")) {
            User user = new User();
            user.setUsername("datcr7");
            user.setPassword(passwordEncoder.encode("123456"));
            user.setEnabled(true);
            user.setEmail("dat@gmail.com");
            user.setFullName("Người dùng");

            // Tạo và gán vai trò cho tài khoản user
            Set<Role> userRoles = new HashSet<>();
            userRoles.add(userRole);
            user.setRoles(userRoles);

            userRepository.save(user);
        }
    }
}
