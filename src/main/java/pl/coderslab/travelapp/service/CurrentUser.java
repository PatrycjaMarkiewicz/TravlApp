package pl.coderslab.travelapp.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class CurrentUser extends User {
    private final pl.coderslab.travelapp.entity.User user;
    public CurrentUser(String username, String password, Collection<?extends GrantedAuthority> authorities,pl.coderslab.travelapp.entity.User user){
        super(username,password,authorities);
        this.user=user;
    }
    public pl.coderslab.travelapp.entity.User getUser(){
        return user;
    };
}
