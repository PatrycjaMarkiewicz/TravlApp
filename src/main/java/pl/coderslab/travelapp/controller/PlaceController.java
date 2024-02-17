package pl.coderslab.travelapp.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.travelapp.entity.Place;
import pl.coderslab.travelapp.entity.User;
import pl.coderslab.travelapp.entity.Vehicle;
import pl.coderslab.travelapp.repository.PlaceRepository;
import pl.coderslab.travelapp.repository.UserRepository;
import pl.coderslab.travelapp.service.CurrentUser;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class PlaceController {
    private final PlaceRepository placeRepo;
    private final UserRepository userRepo;

    public PlaceController(PlaceRepository placeRepo, UserRepository userRepo) {
        this.placeRepo = placeRepo;
        this.userRepo = userRepo;
    }
    @GetMapping("/addPlace")
    public String addPlace(Model model){
        model.addAttribute("place",new Place());
        return "place/addPlace";
    }

    @PostMapping("/addPlace")
    public String addPlaceAccept(Place place,@AuthenticationPrincipal CurrentUser customUser){
        User entityUser = customUser.getUser();
        placeRepo.save(place);
        entityUser.getPlaces().add(place);
        userRepo.save(entityUser);
        return "redirect:";
    }
    @GetMapping("/placeDetails/{id}")
    public String placeDetails(@PathVariable Long id, Model model, @AuthenticationPrincipal CurrentUser customUser){
        User entityUser = customUser.getUser();
        List<Place> collect = entityUser.getPlaces().stream().filter(p -> p.getId().equals(id)).collect(Collectors.toList());
        model.addAttribute("place",collect.get(0));
        return "place/placePanel";
    }
    @GetMapping("/deletePlace/{id}")
    @ResponseBody
    public String deletePlace(@PathVariable Long id, @AuthenticationPrincipal CurrentUser customUser){
        User entityUser = customUser.getUser();
        Place place = entityUser.getPlaces().stream().filter(p -> p.getId().equals(id)).collect(Collectors.toList()).get(0);
        entityUser.getPlaces().remove(place);
        userRepo.save(entityUser);
        placeRepo.delete(place);
        return "Successfully deleted: "+place.getLocationName();
    }
    @GetMapping("/editPlace/{id}")
    public String editPlace(@PathVariable Long id,@AuthenticationPrincipal CurrentUser customUser,Model model) {
        User entityUser = customUser.getUser();
        Place place = entityUser.getPlaces().stream().filter(p -> p.getId().equals(id)).collect(Collectors.toList()).get(0);
        model.addAttribute("place",place);
        return "place/addPlace";
    }
    @PostMapping("/editPlace/{id}")
    @ResponseBody
    public String editSuccessPlace(Place place, @PathVariable Long id,@AuthenticationPrincipal CurrentUser customUser){
        User entityUser = customUser.getUser();
        Place place1 = entityUser.getPlaces().stream().filter(p -> p.getId().equals(id)).collect(Collectors.toList()).get(0);
        place1.setAddress(place.getAddress());
        place1.setLocationName(place.getLocationName());
        placeRepo.save(place);
        return "Successfully edited to: "+place1.getLocationName()+", "+place1.getAddress();
    }
}
