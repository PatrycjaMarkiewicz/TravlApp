package pl.coderslab.travelapp.controller;

import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.travelapp.entity.Travel;
import pl.coderslab.travelapp.service.CurrentUser;
import pl.coderslab.travelapp.service.GoogleMapsApiService;
import pl.coderslab.travelapp.service.UserServiceImp;


@Controller
public class TravelController {
    private final UserServiceImp userService;
    private final GoogleMapsApiService googleMapsApiService;

    public TravelController(UserServiceImp userService, GoogleMapsApiService googleMapsApiService) {
        this.userService = userService;
        this.googleMapsApiService = googleMapsApiService;
    }

    @GetMapping("/startTravel")
    public String startTravel(Model model, @AuthenticationPrincipal CurrentUser currentUser){
        model.addAttribute("user",currentUser.getUser());
        model.addAttribute("travel",new Travel());
        return "travel/startTravel";
    }
    @PostMapping("/startTravel")
    public String startTravelForm(@Valid Travel travel, BindingResult result, @AuthenticationPrincipal CurrentUser currentUser, Model model){
        if(result.hasErrors()){
            model.addAttribute("user",currentUser.getUser());
            return "travel/startTravel";
        }
        userService.addTravel(travel, currentUser.getUser());
        return "redirect:/";
    }


    @GetMapping("/travelDetails/{id}")
    public String travelDetails(@PathVariable Long id, Model model, @AuthenticationPrincipal CurrentUser currentUser){
        model.addAttribute("travel",userService.getTravelById(id, currentUser.getUser()));
        model.addAttribute("map1",googleMapsApiService.getMapUrl(userService.getTravelById(id, currentUser.getUser()).getDestinationAddress()));
        model.addAttribute("map2",googleMapsApiService.getMapUrl(userService.getTravelById(id, currentUser.getUser()).getOriginAddress()));
        return "travel/travelPanel";
    }


    @GetMapping("/deleteTravel/{id}")
    public String deleteTravel(@PathVariable Long id, @AuthenticationPrincipal CurrentUser currentUser){
        userService.deleteTravelById(id, currentUser.getUser());
        return "redirect:/";
    }


    @GetMapping("/editTravel/{id}")
    public String editTravel(@PathVariable Long id,@AuthenticationPrincipal CurrentUser currentUser,Model model) {
        model.addAttribute("travel",userService.getTravelById(id, currentUser.getUser()));
        model.addAttribute("user",currentUser.getUser());
        return "travel/startTravel";
    }
    @PostMapping("/editTravel/{id}")
    public String editSuccessTravel(@Valid Travel travel,BindingResult result, @PathVariable Long id,@AuthenticationPrincipal CurrentUser currentUser,Model model){
        if(result.hasErrors()){
            model.addAttribute("user",currentUser.getUser());
            return "travel/startTravel";
        }
        userService.editTravelById(id, currentUser.getUser(),travel);
        return "redirect:/";
    }
}
