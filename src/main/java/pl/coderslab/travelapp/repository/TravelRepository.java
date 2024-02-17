package pl.coderslab.travelapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.travelapp.entity.Travel;
import pl.coderslab.travelapp.entity.Vehicle;

public interface TravelRepository extends JpaRepository<Travel,Long> {
}
