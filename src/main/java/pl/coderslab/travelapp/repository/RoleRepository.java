package pl.coderslab.travelapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.travelapp.entity.Place;
import pl.coderslab.travelapp.entity.Role;

public interface RoleRepository extends JpaRepository<Role,Integer> {
    Role findByName(String name);
}
