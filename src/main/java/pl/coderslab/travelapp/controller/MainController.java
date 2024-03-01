package pl.coderslab.travelapp.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.coderslab.travelapp.entity.User;
import pl.coderslab.travelapp.service.CurrentUser;


@Controller
public class MainController {
    @GetMapping("/")
    public String showAll(Model model,@AuthenticationPrincipal CurrentUser currentUser){
        User user = currentUser.getUser();
        model.addAttribute("vehicles",user.getVehicles());
        model.addAttribute("places",user.getPlaces());
        model.addAttribute("travels",user.getTravels());
        return "all";
    }
}
