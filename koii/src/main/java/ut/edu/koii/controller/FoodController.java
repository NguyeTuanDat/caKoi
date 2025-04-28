package ut.edu.koii.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ut.edu.koii.models.Koi;
import ut.edu.koii.repository.KoiRepository;

import java.util.Optional;

@Controller
@RequestMapping("kois/{koiId}/support")
public class FoodController {

    @Autowired
    private KoiRepository koiRepo;

    @GetMapping("/food-Result")
    public String calculateFood(@PathVariable Long koiId, Model model) {
        System.out.println("Đang tính thức ăn cho koiId = " + koiId);  // 👈 in ra koiId

        Optional<Koi> koiOpt = koiRepo.findById(koiId);
        if (koiOpt.isEmpty()) {
            return "redirect:/kois/List";
        }

        Koi koi = koiOpt.get();
        double foodRequired = calculateFoodForKoi(koi);

        model.addAttribute("koi", koi);
        model.addAttribute("foodRequired", foodRequired);

        return "support/food-Result";
    }

    private double calculateFoodForKoi(Koi koi) {
        double weight = koi.getWeight();        // kg
        int age = koi.getAgeInMonths();         // tháng
        double ratio;

        if (age <= 3) {
            ratio = 6.0;
        } else if (age <= 6) {
            ratio = 5.0;
        } else if (age <= 12) {
            ratio = 3.0;
        } else {
            ratio = 1.5;
        }

        return weight * (ratio / 100) * 1000;   // gram/ngày
    }
}
