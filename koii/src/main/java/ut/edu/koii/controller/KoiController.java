package ut.edu.koii.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ut.edu.koii.models.Koi;
import ut.edu.koii.repository.KoiRepository;
import ut.edu.koii.service.KoiService;

import java.util.Optional;

@Controller
@RequestMapping("kois")
public class KoiController {

    @Autowired
    private KoiService koiService;
    @Autowired
    private KoiRepository koiRepository;

    // 1. Hiển thị danh sách Koi
    @GetMapping("/List")
    public String list(Model model) {
        model.addAttribute("kois", koiService.list());
        return "Koi/list";  // trả về trang list.html
    }

    // 2. Hiển thị form tạo Koi mới
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("koi", new Koi());
        return "Koi/form";  // trả về trang form.html
    }

    // 3. Hiển thị form chỉnh sửa Koi
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<Koi> opt = koiRepository.findById(id);
        if (opt.isEmpty()) {
            return "redirect:/kois/List";  // không tìm thấy Koi, chuyển về danh sách
        }
        model.addAttribute("koi", opt.get());
        return "Koi/form";  // trả về trang form.html để chỉnh sửa
    }

    // 4. Xử lý lưu cả tạo và cập nhật Koi
    @PostMapping
    public String save(
            @ModelAttribute("koi") @Valid Koi koi,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            return "Koi/form";  // nếu có lỗi, quay lại form
        }
        koiRepository.save(koi);  // lưu vào cơ sở dữ liệu
        return "redirect:/kois/List";  // chuyển đến danh sách
    }

    // 5. Xóa Koi
    @PostMapping("/{id}/delete")
    public String deleteKoi(@PathVariable Long id) {
        koiRepository.deleteById(id);  // xóa Koi theo id
        return "redirect:/kois/List";  // chuyển về danh sách
    }
}
