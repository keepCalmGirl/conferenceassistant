package ifit.cluster.cassistant.controller;

import ifit.cluster.cassistant.domain.Role;
import ifit.cluster.cassistant.domain.User;
import ifit.cluster.cassistant.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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

    @PostMapping("/users/profile")
    public String getProfile(@RequestParam(name = "email") String email, Model model){
        Optional<User> userByEmail = userService.getUserByEmail(email);
        if (userByEmail.isPresent()){
            User user = userByEmail.get();
            model.addAttribute("user", user);
            model.addAttribute("roles", Role.values());
            return "profile";
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/users")
    public String getAllUsers(Model model){
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("roles", Role.values());
        model.addAttribute("userMap", new User());
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

    @PostMapping("/users/save")
    public String userSave(@ModelAttribute User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userService.saveUser(user);
        return "redirect:/users";
    }

    @PostMapping("/users/update")
    public String userUpdateEmail(@ModelAttribute User user, Model model) {
        Optional<User> userById = userService.getUserById(user.getId());
        userById.ifPresent(u -> {
            if (user.getEmail() == null) {
                u.setFirstName(user.getFirstName());
                u.setLastName(user.getLastName());
                u.setPhone(user.getPhone());
                u.setPhoto(user.getPhoto());
                u.setRole(user.getRole());
            } else {
                u.setEmail(user.getEmail());
                if (!u.getEmail().equals(user.getEmail()))
                    u.setEmail(user.getEmail());
                if (!u.getPassword().equals(user.getPassword()))
                    u.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            }
            model.addAttribute("user", userService.saveUser(u));
            model.addAttribute("roles", Role.values());
        });
        return "profile";
    }

    @PostMapping(value = "/users/submit/new")
    @ResponseBody
    public String userSubmitNew(@RequestBody User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userService.saveUser(user);
        return "{'text': 'fooooo'}";
    }

    @PostMapping(value = "/cur",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void changeRole(@RequestBody User user){
        Optional<User> userOptional = userService.getUserById(user.getId());
        userOptional.ifPresent(u -> {
            u.setRole(user.getRole());
            userService.saveUser(u);
        });
    }
}
