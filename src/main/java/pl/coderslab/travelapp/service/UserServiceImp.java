package pl.coderslab.travelapp.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.coderslab.travelapp.entity.*;
import pl.coderslab.travelapp.repository.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Collectors;

@Service
public class UserServiceImp implements UserService{
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final PlaceRepository placeRepo;
    private final VehicleRepository vehicleRepo;
    private final TravelRepository travelRepo;
    private final GoogleMapsApiService googleMapsApiService;
    private final EmailService emailService;

    public UserServiceImp(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder, PlaceRepository placeRepo, VehicleRepository vehicleRepo, TravelRepository travelRepo, GoogleMapsApiService googleMapsApiService, EmailService emailService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.placeRepo = placeRepo;
        this.vehicleRepo = vehicleRepo;
        this.travelRepo = travelRepo;
        this.googleMapsApiService = googleMapsApiService;
        this.emailService = emailService;
    }

    @Override
    public User findByUserName(String username) {
        return userRepository.findByUsername(username);
    }
    @Override
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(1);
        Role userRole = roleRepository.findByName("ROLE_USER");
        user.setRoles(new HashSet<>(Arrays.asList(userRole)));
        userRepository.save(user);
        emailService.sendEmail(user.getEmail(),"created account","successfully created new account. Welcome "+user.getUsername());
    };

    //Place
    @Override
    public void addPlace(Place place, User user) {
        placeRepo.save(place);
        user.getPlaces().add(place);
        userRepository.save(user);
    }
    @Override
    public Place getPlaceById(Long id, User user) {
        return user.getPlaces().stream().filter(p -> p.getId().equals(id)).collect(Collectors.toList()).get(0);
    }
    @Override
    public void deletePlaceById(Long id, User user) {
        Place place = user.getPlaces().stream().filter(p -> p.getId().equals(id)).collect(Collectors.toList()).get(0);
        user.getPlaces().remove(place);
        userRepository.save(user);
    }
    @Override
    public void editPlaceById(Long id, User user,Place place) {
        Place place1 = getPlaceById(id,user);
        place1.setAddress(place.getAddress());
        place1.setLocationName(place.getLocationName());
        placeRepo.save(place);
    }

    //Vehicle
    @Override
    public void addVehicle(Vehicle vehicle, User user) {
        vehicleRepo.save(vehicle);
        user.getVehicles().add(vehicle);
        userRepository.save(user);
    }
    @Override
    public Vehicle getVehicleById(Long id, User user) {
        return user.getVehicles().stream().filter(w -> w.getId().equals(id)).collect(Collectors.toList()).get(0);
    }
    @Override
    public void deleteVehicleById(Long id, User user) {
        Vehicle vehicle = user.getVehicles().stream().filter(w -> w.getId().equals(id)).collect(Collectors.toList()).get(0);
        user.getVehicles().remove(vehicle);
        userRepository.save(user);
    }
    @Override
    public void editVehicleById(Long id, User user, Vehicle vehicle) {
        Vehicle vehicle1 = getVehicleById(id,user);
        vehicle1.setName(vehicle.getName());
        vehicle1.setAvgSpeed(vehicle.getAvgSpeed());
        vehicleRepo.save(vehicle);
    }

    //Travel
    @Override
    public void addTravel(Travel travel, User user) {
        double distance = googleMapsApiService.getDistance(travel.getOriginAddress(),travel.getDestinationAddress());
        if(distance==-1){
            return;
        }
        travel.setDistance(distance);
        travel.setHours(Integer.parseInt(String.format("%.0f",Math.floor(travel.getDistance()/travel.getVehicleSpeed()))));
        travel.setMinutes(Integer.parseInt(String.format("%.0f",travel.getDistance()%travel.getVehicleSpeed())));
        travel.setWaterInLiters(Double.parseDouble(String.format("%.1f", googleMapsApiService.countWaterInLiters(travel))));
        travelRepo.save(travel);
        user.getTravels().add(travel);
        userRepository.save(user);
    }
    @Override
    public Travel getTravelById(Long id, User user) {
        return user.getTravels().stream().filter(t -> t.getId().equals(id)).collect(Collectors.toList()).get(0);
    }
    @Override
    public void deleteTravelById(Long id, User user) {
        Travel travel = user.getTravels().stream().filter(t -> t.getId().equals(id)).collect(Collectors.toList()).get(0);
        user.getTravels().remove(travel);
        userRepository.save(user);
    }
    @Override
    public void editTravelById(Long id, User user, Travel travel) {
        Travel travel1 = getTravelById(id,user);
        travel1.setTitle(travel.getTitle());
        travel1.setOriginAddress(travel.getOriginAddress());
        travel1.setDestinationAddress(travel.getDestinationAddress());
        travel1.setVehicleSpeed(travel.getVehicleSpeed());
        travel1.setPlannedDate(travel.getPlannedDate());
        travelRepo.save(travel1);
    }
}
