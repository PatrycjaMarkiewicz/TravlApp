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
import pl.coderslab.travelapp.entity.Vehicle;
import pl.coderslab.travelapp.service.CurrentUser;
import pl.coderslab.travelapp.service.UserServiceImp;

@Controller
public class VehicleController {
    private final UserServiceImp userService;

    public VehicleController(UserServiceImp userService) {
        this.userService = userService;
    }

    @GetMapping("/addVehicle")
    public String addVehicle(Model model){
        model.addAttribute("vehicle",new Vehicle());
        return "vehicle/addVehicle";
    }

    @PostMapping("/addVehicle")
    public String addVehicleAccept(@Valid Vehicle vehicle, BindingResult result, @AuthenticationPrincipal CurrentUser currentUser){
        if(result.hasErrors()){
            return "vehicle/addVehicle";
        }
        userService.addVehicle(vehicle, currentUser.getUser());
        return "redirect:/";
    }


    @GetMapping("/vehicleDetails/{id}")
    public String vehicleDetails(@PathVariable Long id, Model model, @AuthenticationPrincipal CurrentUser currentUser){
        model.addAttribute("vehicle",userService.getVehicleById(id, currentUser.getUser()));
        return "vehicle/vehiclePanel";
    }
    @GetMapping("/deleteVehicle/{id}")
    public String deleteVehicle(@PathVariable Long id, @AuthenticationPrincipal CurrentUser currentUser){
        userService.deleteVehicleById(id, currentUser.getUser());
        return "redirect:/";
    }
    @GetMapping("/editVehicle/{id}")
    public String editVehicle(@PathVariable Long id,@AuthenticationPrincipal CurrentUser currentUser,Model model) {
        model.addAttribute("vehicle",userService.getVehicleById(id, currentUser.getUser()));
        return "vehicle/addVehicle";
    }
    @PostMapping("/editVehicle/{id}")
    public String editSuccessVehicle(@Valid Vehicle vehicle,BindingResult result, @PathVariable Long id,@AuthenticationPrincipal CurrentUser currentUser){
        if(result.hasErrors()){
            return "vehicle/addVehicle";
        }
        userService.editVehicleById(id, currentUser.getUser(), vehicle);
        return "redirect:/";
    }
}
