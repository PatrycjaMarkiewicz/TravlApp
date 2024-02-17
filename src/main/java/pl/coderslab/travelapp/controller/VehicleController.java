package pl.coderslab.travelapp.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.travelapp.entity.Place;
import pl.coderslab.travelapp.entity.Travel;
import pl.coderslab.travelapp.entity.User;
import pl.coderslab.travelapp.entity.Vehicle;
import pl.coderslab.travelapp.repository.UserRepository;
import pl.coderslab.travelapp.repository.VehicleRepository;
import pl.coderslab.travelapp.service.CurrentUser;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class VehicleController {
    private final VehicleRepository vehicleRepo;
    private final UserRepository userRepo;

    public VehicleController(VehicleRepository vehicleRepo, UserRepository userRepo) {
        this.vehicleRepo = vehicleRepo;
        this.userRepo = userRepo;
    }

    @GetMapping("/addVehicle")
    public String addVehicle(Model model){
        model.addAttribute("vehicle",new Vehicle());
        return "vehicle/addVehicle";
    }

    @PostMapping("/addVehicle")
    public String addVehicleAccept(Vehicle vehicle,@AuthenticationPrincipal CurrentUser customUser){
        User entityUser = customUser.getUser();
        vehicleRepo.save(vehicle);
        entityUser.getVehicles().add(vehicle);
        userRepo.save(entityUser);
        return "redirect:";
    }


    @GetMapping("/vehicleDetails/{id}")
    public String vehicleDetails(@PathVariable Long id, Model model, @AuthenticationPrincipal CurrentUser customUser){
        User entityUser = customUser.getUser();
        List<Vehicle> collect = entityUser.getVehicles().stream().filter(w -> w.getId().equals(id)).collect(Collectors.toList());
        model.addAttribute("vehicle",collect.get(0));
        return "vehicle/vehiclePanel";
    }
    @GetMapping("/deleteVehicle/{id}")
    @ResponseBody
    public String deleteVehicle(@PathVariable Long id, @AuthenticationPrincipal CurrentUser customUser){
        User entityUser = customUser.getUser();
        Vehicle vehicle = entityUser.getVehicles().stream().filter(w -> w.getId().equals(id)).collect(Collectors.toList()).get(0);
        entityUser.getVehicles().remove(vehicle);
        userRepo.save(entityUser);
        vehicleRepo.delete(vehicle);
        return "Successfully deleted: "+vehicle.getName();
    }
    @GetMapping("/editVehicle/{id}")
    public String editVehicle(@PathVariable Long id,@AuthenticationPrincipal CurrentUser customUser,Model model) {
        User entityUser = customUser.getUser();
        Vehicle vehicle = entityUser.getVehicles().stream().filter(v -> v.getId().equals(id)).collect(Collectors.toList()).get(0);
        model.addAttribute("vehicle",vehicle);
        return "vehicle/addVehicle";
    }
    @PostMapping("/editVehicle/{id}")
    @ResponseBody
    public String editSuccessVehicle(Vehicle vehicle, @PathVariable Long id,@AuthenticationPrincipal CurrentUser customUser){
        User entityUser = customUser.getUser();
        Vehicle vehicle1 = entityUser.getVehicles().stream().filter(v -> v.getId().equals(id)).collect(Collectors.toList()).get(0);
        vehicle1.setName(vehicle.getName());
        vehicle1.setAvgSpeed(vehicle.getAvgSpeed());
        vehicleRepo.save(vehicle);
        return "Successfully edited to: "+vehicle1.getName()+", "+vehicle1.getAvgSpeed();
    }
}
