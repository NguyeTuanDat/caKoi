package ut.edu.koii.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ut.edu.koii.models.Product;
import ut.edu.koii.repository.ProductRepository;

import java.util.Optional;

@Controller
@RequestMapping("products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    // 1. Hiển thị danh sách sản phẩm
    @GetMapping("/List")
    public String list(Model model) {
        model.addAttribute("products", productRepository.findAll());
        return "Product/list";
    }

    // 2. Hiển thị form tạo sản phẩm mới
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("product", new Product());
        return "Product/form";
    }

    // 3. Hiển thị form chỉnh sửa sản phẩm
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<Product> opt = productRepository.findById(id);
        if (opt.isEmpty()) {
            return "redirect:/products/List";
        }
        model.addAttribute("product", opt.get());
        return "Product/form";
    }

    // 4. Xử lý lưu (cả tạo mới và cập nhật)
    @PostMapping
    public String save(@ModelAttribute("product") @Valid Product product, BindingResult result) {
        if (result.hasErrors()) {
            return "Product/form";
        }
        productRepository.save(product);
        return "redirect:/products/List";
    }

    // 5. Xóa sản phẩm
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        productRepository.deleteById(id);
        return "redirect:/products/List";
    }
}
