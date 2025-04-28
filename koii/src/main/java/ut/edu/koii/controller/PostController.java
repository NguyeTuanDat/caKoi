package ut.edu.koii.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import ut.edu.koii.models.Post;
import ut.edu.koii.repository.PostRepository;

import java.util.Optional;

@Controller
@RequestMapping("/news")
public class PostController {

    @Autowired
    private PostRepository postRepo;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("posts", postRepo.findAll());
        return "news/list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("post", new Post());
        return "news/form";
    }

    @PostMapping
    public String save(@ModelAttribute("post") @Valid Post post, BindingResult result) {
        if (result.hasErrors()) return "news/form";
        postRepo.save(post);
        return "redirect:/news";
    }

    @GetMapping("/{id}")
    public String view(@PathVariable Long id, Model model) {
        Optional<Post> opt = postRepo.findById(id);
        if (opt.isEmpty()) return "redirect:/news";
        model.addAttribute("post", opt.get());
        return "news/view";
    }
}
