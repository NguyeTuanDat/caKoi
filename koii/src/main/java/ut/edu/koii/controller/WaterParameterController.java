package ut.edu.koii.controller;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ut.edu.koii.models.Pond;
import ut.edu.koii.models.WaterParameter;
import ut.edu.koii.repository.PondRepository;
import ut.edu.koii.service.WaterParameterService;

@Controller
@RequestMapping("ponds/{pondId}/water")
public class WaterParameterController {

    private static final Logger logger = LoggerFactory.getLogger(WaterParameterController.class);

    @Autowired
    private WaterParameterService waterService;

    @Autowired
    private PondRepository pondRepo;

    @GetMapping("/list")
    public String list(@PathVariable Long pondId, Model model) {
        try {
            Pond pond = pondRepo.findById(pondId)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid pond Id: " + pondId));
            model.addAttribute("pond", pond);
            model.addAttribute("params", waterService.listByPond(pondId));
            logger.info("Showing list for pondId: {}", pondId);
            return "Water/list";
        } catch (Exception e) {
            logger.error("Error displaying water parameters list for pondId: {}. Error: {}", pondId, e.getMessage(), e);
            model.addAttribute("errorMessage", "Lỗi khi hiển thị danh sách thông số nước: " + e.getMessage());
            return "error"; // Đảm bảo có file error.html hoặc thay bằng view khác
        }
    }

    @GetMapping("/create")
    public String showForm(@PathVariable Long pondId, Model model) {
        Pond pond = pondRepo.findById(pondId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid pond Id: " + pondId));
        WaterParameter param = new WaterParameter();
        param.setPond(pond);
        model.addAttribute("param", param);
        model.addAttribute("pond", pond);
        logger.info("Showing form for pondId: {}, param: {}", pondId, param);
        return "Water/form";
    }

    @PostMapping
    public String save(@PathVariable Long pondId,
                       @ModelAttribute("param") @Valid WaterParameter param,
                       BindingResult result,
                       Model model) {
        Pond pond = pondRepo.findById(pondId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid pond Id: " + pondId));

        logger.info("Received WaterParameter: id={}, measuredAt={}, pH={}, pondId={}",
                param.getId(), param.getMeasuredAt(), param.getpH(),
                param.getPond() != null ? param.getPond().getId() : null);

        if (result.hasErrors()) {
            logger.warn("Validation errors: {}", result.getAllErrors());
            model.addAttribute("pond", pond);
            model.addAttribute("param", param);
            model.addAttribute("errorMessage", "Vui lòng kiểm tra và sửa các lỗi trong form.");
            return "Water/form";
        }

        param.setPond(pond);

        try {
            waterService.save(param);
            logger.info("Successfully saved WaterParameter for pondId: {}", pondId);
        } catch (Exception e) {
            logger.error("Error saving WaterParameter: {}", e.getMessage(), e);
            model.addAttribute("pond", pond);
            model.addAttribute("param", param);
            model.addAttribute("errorMessage", "Lỗi khi lưu thông số nước: " + e.getMessage());
            return "Water/form";
        }

        return "redirect:/ponds/" + pondId + "/water/list";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long pondId, @PathVariable Long id) {
        waterService.delete(id);
        return "redirect:/ponds/" + pondId + "/water/list";
    }
}