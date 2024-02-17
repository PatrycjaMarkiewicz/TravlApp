package pl.coderslab.travelapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.travelapp.entity.Place;
import pl.coderslab.travelapp.entity.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle,Long> {
}
