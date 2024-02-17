package pl.coderslab.travelapp.service;

import pl.coderslab.travelapp.entity.User;

public interface UserService {
    User findByUserName(String name);

    void saveUser(User user);
}
