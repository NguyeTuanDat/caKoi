package ut.edu.koii.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ut.edu.koii.models.Role;
import ut.edu.koii.service.RoleService;

import java.util.List;
import java.util.Optional;

@Controller                // ← để xử lý view (Thymeleaf)
@RequestMapping("/roles")  // URL gốc cho giao diện web
public class RoleController {

    @Autowired
    private RoleService roleService;

    // ===== WEB =====
    @GetMapping
    public String showRoles(Model model) {
        List<Role> roles = roleService.getAllRoles();
        model.addAttribute("roles", roles);
        return "roles";     // sẽ render src/main/resources/templates/roles.html
    }

    // ===== API =====
    @RestController
    @RequestMapping("/api/roles")
    static class RoleRestController {
        @Autowired
        private RoleService roleService;

        @GetMapping
        public ResponseEntity<List<Role>> getAllRoles() {
            return ResponseEntity.ok(roleService.getAllRoles());
        }

        @PostMapping
        public ResponseEntity<Role> saveRole(@RequestBody Role role) {
            return ResponseEntity.ok(roleService.saveRole(role));
        }
    }
}
