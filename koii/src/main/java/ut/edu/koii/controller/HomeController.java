package ut.edu.koii.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class HomeController {

    // Trang chủ, hiển thị tên người dùng nếu đã login
    @GetMapping("/")
    public String index(Model model, Principal principal) {
        String username = (principal != null) ? principal.getName() : "Khách";
        model.addAttribute("username", username);

        return "index";    // src/main/resources/templates/index.html
    }

    // Trang login
    @GetMapping("/login")
    public String login() {
        return "login";    // src/main/resources/templates/login.html
    }

    // Trang dành cho ROLE_ADMIN
    @GetMapping("/admin")
    public String admin(Model model, Principal principal) {
        model.addAttribute("username", principal.getName());
        return "admin";    // src/main/resources/templates/admin.html
    }

    // Trang dành cho ROLE_USER (và ADMIN nếu cho phép)
    @GetMapping("/user")
    public String user(Model model, Principal principal) {
        model.addAttribute("username", principal.getName());
        return "user";     // src/main/resources/templates/user.html
    }
    @GetMapping("/Pond")
    public String pondPage() {
        return "Pond"; // Trang chứa thông tin hồ cá sau khi đăng nhập
    }


}
