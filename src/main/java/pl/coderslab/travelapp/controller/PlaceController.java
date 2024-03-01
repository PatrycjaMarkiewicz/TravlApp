package pl.coderslab.travelapp.controller;

import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.travelapp.entity.Place;
import pl.coderslab.travelapp.service.CurrentUser;
import pl.coderslab.travelapp.service.GoogleMapsApiService;
import pl.coderslab.travelapp.service.UserServiceImp;


@Controller
public class PlaceController {
    private final UserServiceImp userService;
    private final GoogleMapsApiService googleMapsApiService;

    public PlaceController(UserServiceImp userService, GoogleMapsApiService googleMapsApiService) {
        this.userService = userService;
        this.googleMapsApiService = googleMapsApiService;
    }

    @GetMapping("/addPlace")
    public String addPlace(Model model){
        model.addAttribute("place",new Place());
        return "place/addPlace";
    }
    @PostMapping("/addPlace")
    public String addPlaceAccept(@Valid Place place, BindingResult result, @AuthenticationPrincipal CurrentUser currentUser){
        if(result.hasErrors()){
            return "place/addPlace";
        }
        userService.addPlace(place, currentUser.getUser());
        return "redirect:/";
    }


    @GetMapping("/placeDetails/{id}")
    public String placeDetails(@PathVariable Long id, Model model, @AuthenticationPrincipal CurrentUser currentUser){
        model.addAttribute("place",userService.getPlaceById(id, currentUser.getUser()));
        model.addAttribute("map",googleMapsApiService.getMapUrl(userService.getPlaceById(id, currentUser.getUser()).getAddress()));
        return "place/placePanel";
    }

    @GetMapping("/deletePlace/{id}")
    public String deletePlace(@PathVariable Long id, @AuthenticationPrincipal CurrentUser currentUser){
        userService.deletePlaceById(id, currentUser.getUser());
        return "redirect:/";
    }

    @GetMapping("/editPlace/{id}")
    public String editPlace(@PathVariable Long id,@AuthenticationPrincipal CurrentUser currentUser,Model model) {
        model.addAttribute("place",userService.getPlaceById(id, currentUser.getUser()));
        return "place/addPlace";
    }
    
    @PostMapping("/editPlace/{id}")
    public String editSuccessPlace(@Valid Place place,BindingResult result, @PathVariable Long id,@AuthenticationPrincipal CurrentUser currentUser){
        if(result.hasErrors()){
            return "place/addPlace";
        }
        userService.editPlaceById(id, currentUser.getUser(),place);
        return "redirect:/";
    }
}