package ut.edu.koii.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ut.edu.koii.models.Pond;
import ut.edu.koii.repository.PondRepository;
import ut.edu.koii.service.PondService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("ponds")
public class    PondController {

    @Autowired
    private PondService pondService;
    @Autowired
    private PondRepository pondRepository;

    @GetMapping("/List") // Đổi đường dẫn từ "/Pond" thành "/pondList"
    public String list(Model model, @AuthenticationPrincipal User principal) {
        // Logic để lấy danh sách hồ cá
        model.addAttribute("ponds", pondService.getAllPonds());
        return "Pond/list"; // Trả về view tương ứng
    }


    // 2. Hiển thị form tạo hồ mới
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("pond", new Pond());
        return "Pond/form";
    }

    // 3. Hiển thị form chỉnh sửa hồ
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<Pond> opt = pondRepository.findById(id);
        if (opt.isEmpty()) {
            // Nếu không tìm thấy, chuyển hướng về danh sách
            return "redirect:/ponds/List";
        }
        model.addAttribute("pond", opt.get());
        return "Pond/form";
    }

    // 4. Xử lý lưu cả create và update
    @PostMapping
    public String save(
            @ModelAttribute("pond") @Valid Pond pond,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            return "Pond/form";
        }
        MultipartFile file = pond.getImageFile();
        if (file != null && !file.isEmpty()) {
            try {
                String filename = UUID.randomUUID() + "_" + file.getOriginalFilename();
                Path uploadPath = Paths.get("uploads/");
                Files.createDirectories(uploadPath);
                Files.copy(file.getInputStream(), uploadPath.resolve(filename), StandardCopyOption.REPLACE_EXISTING);
                pond.setImageUrl("/uploads/" + filename);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        pondRepository.save(pond);
        return "redirect:/ponds/List";
    }

    // 5. Xóa hồ
    @PostMapping("/{id}/delete")
    public String deletePond(@PathVariable Long id) {
        pondRepository.deleteById(id);
        return "redirect:/ponds/List";
    }
}
