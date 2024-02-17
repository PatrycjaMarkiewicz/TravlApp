package pl.coderslab.travelapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.travelapp.entity.Place;

public interface PlaceRepository extends JpaRepository<Place,Long> {
}
