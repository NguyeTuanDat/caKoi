package ut.edu.koii.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ut.edu.koii.models.Pond;
import ut.edu.koii.repository.PondRepository;

import java.util.Optional;

@Controller
@RequestMapping("ponds/{pondId}/support")
public class SaltController {

    @Autowired
    private PondRepository pondRepo;

    // Tính toán muối trực tiếp mà không cần form
    @GetMapping("/salt-Result")
    public String calculateSalt(@PathVariable Long pondId, Model model) {
        Optional<Pond> pondOpt = pondRepo.findById(pondId);
        if (pondOpt.isEmpty()) {
            return "redirect:/ponds/List"; // Điều hướng nếu không tìm thấy hồ
        }

        // GPiả sử bạn có công thức tính muối ở đây
        Pond pond = pondOpt.get();
        double saltRequired = calculateSaltForPond(pond);

        // Lưu kết quả vào model
        model.addAttribute("saltRequired", saltRequired);
        model.addAttribute("pond", pond);

        return "support/salt-Result"; // Trả về trang kết quả
    }

    // Phương thức tính toán muối
    private double calculateSaltForPond(Pond pond) {
        // Ví dụ tính toán: Bạn có thể thay đổi công thức này theo yêu cầu
        return pond.getWaterVolume() * 0.03; // Giả sử muối cần thiết = thể tích nước * 0.03
    }
}
