package ifit.cluster.cassistant.controller;

import ifit.cluster.cassistant.domain.User;
import ifit.cluster.cassistant.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class UserController {


    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserController(UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @GetMapping("/users/{id}")
    public String getUser(@PathVariable("id") Long id, Model model){
        Optional<User> userById = userService.getUserById(id);
        if (userById.isPresent()){
            model.addAttribute("user", userById.get());
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
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        model.addAttribute("user", userService.saveUser(user));
        return "profile";
    }

    @PostMapping(value = "/users/submit/new")
    @ResponseBody
    public String userSubmitNew(@RequestBody User user) {
        System.out.println(user.getFirstName());
        System.out.println(user.getLastName());
        System.out.println(user.getEmail());
        System.out.println(user.getPassword());
        System.out.println(user.getPhone());
        System.out.println("HAHA");
        return "{'text': 'fooooo'}";
    }
}
