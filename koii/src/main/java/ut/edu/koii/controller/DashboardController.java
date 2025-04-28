package ut.edu.koii.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ut.edu.koii.models.Koi;
import ut.edu.koii.repository.KoiRepository;
import ut.edu.koii.repository.PondRepository;

@Controller
public class DashboardController {

    @Autowired
    private KoiRepository koiRepo;

    @Autowired
    private PondRepository pondRepo;

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        long totalKois = koiRepo.count();
        long totalPonds = pondRepo.count();
        double totalFood = koiRepo.findAll().stream()
                .mapToDouble(koi -> calculateFood(koi))
                .sum();

        model.addAttribute("totalKois", totalKois);
        model.addAttribute("totalPonds", totalPonds);
        model.addAttribute("totalFood", totalFood);

        return "dashboard/index";
    }

    private double calculateFood(Koi koi) {
        double ratio = koi.getAgeInMonths() <= 3 ? 6.0 :
                koi.getAgeInMonths() <= 6 ? 5.0 :
                        koi.getAgeInMonths() <= 12 ? 3.0 : 1.5;
        return koi.getWeight() * (ratio / 100) * 1000;
    }
}
