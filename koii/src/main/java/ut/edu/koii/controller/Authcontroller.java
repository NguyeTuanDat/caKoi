package ut.edu.koii.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ut.edu.koii.models.Role;
import ut.edu.koii.models.User;
import ut.edu.koii.repository.RoleRepository;
import ut.edu.koii.repository.UserRepository;

@Controller
public class Authcontroller {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public Authcontroller(UserRepository userRepository,PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder =  passwordEncoder;
        this.roleRepository = roleRepository;
    }


    @GetMapping("/register")
    public String showForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("user") @Valid User user,
                           BindingResult result, Model model) {
        System.out.println("Email received: " + user.getEmail());
        System.out.println("Confirm pass: " + user.getConfirmPassword());

        // Kiểm tra mật khẩu xác nhận
        if (!user.getPassword().equals(user.getConfirmPassword())) {
            result.rejectValue("confirmPassword", null, "Mật khẩu xác nhận không khớp");
        }

        // Kiểm tra tên đăng nhập đã tồn tại
        if (userRepository.existsByUsername(user.getUsername())) {
            result.rejectValue("username", null, "Tên đăng nhập đã tồn tại");
        }

        // Kiểm tra email đã tồn tại
        if (userRepository.existsByEmail(user.getEmail())) {
            result.rejectValue("email", null, "Email đã được đăng ký");
        }

        // Nếu có lỗi, quay lại trang đăng ký và hiển thị lỗi
        if (result.hasErrors()) {
            return "register";
        }

        // Mã hóa mật khẩu và lưu người dùng
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role role = roleRepository.findById(1L).orElseThrow(() -> new RuntimeException("Vai trò không tồn tại"));
        user.setEnabled(true);
        user.getRoles().add(role);
        userRepository.save(user);
        return "redirect:/login?registered";
    }


}
