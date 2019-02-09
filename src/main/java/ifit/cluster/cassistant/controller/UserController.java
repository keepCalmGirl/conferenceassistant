package ifit.cluster.cassistant.controller;

import ifit.cluster.cassistant.domain.User;
import ifit.cluster.cassistant.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users/{id}")
    public String getUser(@PathVariable("id") Long id, Model model){
        Optional<User> userByEmail = userService.getUserById(id);
        if (userByEmail.isPresent()){
            model.addAttribute("user", userByEmail.get());
            return "profile";
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/users")
    public String getAllUsers(Model model){
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }

    @GetMapping("/users/form")
    public String userForm(Model model) {
        model.addAttribute("user", new User());
        return "user_form";
    }

    @PostMapping("/users/submit")
    public String userSubmit(@ModelAttribute User user, Model model) {
        model.addAttribute("user", userService.saveUser(user));
        return "profile";
    }
}
