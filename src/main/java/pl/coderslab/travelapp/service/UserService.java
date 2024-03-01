package pl.coderslab.travelapp.service;

import pl.coderslab.travelapp.entity.Place;
import pl.coderslab.travelapp.entity.Travel;
import pl.coderslab.travelapp.entity.User;
import pl.coderslab.travelapp.entity.Vehicle;

public interface UserService {
    User findByUserName(String name);

    void saveUser(User user);

    void addPlace(Place place,User user);
    Place getPlaceById(Long id, User user);
    void deletePlaceById(Long id, User user);
    void editPlaceById(Long id,User user,Place place);

    void addVehicle(Vehicle vehicle, User user);
    Vehicle getVehicleById(Long id,User user);
    void deleteVehicleById(Long id, User user);
    void editVehicleById(Long id, User user, Vehicle vehicle);

    void addTravel(Travel travel,User user);
    Travel getTravelById(Long id,User user);
    void  deleteTravelById(Long id, User user);
    void editTravelById(Long id, User user, Travel travel);
}
