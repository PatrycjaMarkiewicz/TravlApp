package pl.coderslab.travelapp.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.travelapp.entity.Travel;
import pl.coderslab.travelapp.entity.User;
import pl.coderslab.travelapp.entity.Vehicle;
import pl.coderslab.travelapp.repository.TravelRepository;
import pl.coderslab.travelapp.repository.UserRepository;
import pl.coderslab.travelapp.service.CurrentUser;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class TravelController {
    private final TravelRepository travelRepo;
    private final UserRepository userRepo;

    public TravelController(TravelRepository travelRepo, UserRepository userRepo) {
        this.travelRepo = travelRepo;
        this.userRepo = userRepo;
    }

    @GetMapping("/startTravel")
    public String startTravel(Model model, @AuthenticationPrincipal CurrentUser customUser){
        User entityUser = customUser.getUser();
        model.addAttribute("user",entityUser);
        model.addAttribute("travel",new Travel());
        return "travel/startTravel";
    }

    @PostMapping("/startTravel")
    public String startTravelForm(Travel travel, @AuthenticationPrincipal CurrentUser customUser){
        User entityUser = customUser.getUser();
        travelRepo.save(travel);
        entityUser.getTravels().add(travel);
        userRepo.save(entityUser);
        return "redirect:";
    }
    @GetMapping("/travelDetails/{id}")
    public String travelDetails(@PathVariable Long id, Model model, @AuthenticationPrincipal CurrentUser customUser){
        User entityUser = customUser.getUser();
        Travel travel = entityUser.getTravels().stream().filter(t -> t.getId().equals(id)).collect(Collectors.toList()).get(0);
        model.addAttribute("travel",travel);
        return "travel/travelPanel";
    }

    @GetMapping("/deleteTravel/{id}")
    @ResponseBody
    public String deleteTravel(@PathVariable Long id, @AuthenticationPrincipal CurrentUser customUser){
        User entityUser = customUser.getUser();
        Travel travel = entityUser.getTravels().stream().filter(t -> t.getId().equals(id)).collect(Collectors.toList()).get(0);
        entityUser.getTravels().remove(travel);
        userRepo.save(entityUser);
        travelRepo.delete(travel);
        return "Successfully deleted: "+travel.getTitle();
    }
    @GetMapping("/editTravel/{id}")
    public String editTravel(@PathVariable Long id,@AuthenticationPrincipal CurrentUser customUser,Model model) {
        User entityUser = customUser.getUser();
        Travel travel = entityUser.getTravels().stream().filter(t -> t.getId().equals(id)).collect(Collectors.toList()).get(0);
        model.addAttribute("travel",travel);
        model.addAttribute("user",entityUser);
        return "travel/startTravel";
    }
    @PostMapping("/editTravel/{id}")
    @ResponseBody
    public String editSuccessTravel(Travel travel, @PathVariable Long id,@AuthenticationPrincipal CurrentUser customUser){
        User entityUser = customUser.getUser();
        Travel travel1 = entityUser.getTravels().stream().filter(t -> t.getId().equals(id)).collect(Collectors.toList()).get(0);
        travel1.setTitle(travel.getTitle());
        travel1.setOriginAddress(travel.getOriginAddress());
        travel1.setDestinationAddress(travel.getDestinationAddress());
        travel1.setVehicleSpeed(travel.getVehicleSpeed());
        travel1.setPlannedDate(travel.getPlannedDate());
        travelRepo.save(travel1);
        return "Successfully edited to: "+travel1.getTitle()+" from: "+travel1.getOriginAddress()+" to: "+travel1.getDestinationAddress()+" on: "+travel1.getPlannedDate();
    }
}
