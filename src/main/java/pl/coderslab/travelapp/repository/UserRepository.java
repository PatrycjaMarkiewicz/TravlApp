package pl.coderslab.travelapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.travelapp.entity.Role;
import pl.coderslab.travelapp.entity.User;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);
}
