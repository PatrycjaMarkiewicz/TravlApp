package pl.coderslab.travelapp.controller;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.travelapp.entity.User;
import pl.coderslab.travelapp.service.UserService;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/create-user")
    public String formCreateUser(Model model){
        model.addAttribute("user",new User());
        return "addUser";
    }

    @PostMapping("/create-user")
    public String createUser(@Valid User user, BindingResult result){
        if(result.hasErrors()){
            return "addUser";
        }
        userService.saveUser(user);
        return "redirect:login";
    }
}
