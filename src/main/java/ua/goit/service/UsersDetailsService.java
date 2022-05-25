package ua.goit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ua.goit.model.auth.UserPrincipal;
import ua.goit.model.dto.UserDto;

@Service
public class UsersDetailsService implements UserDetailsService {
    private UserService userService;

    @Autowired
    public UsersDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDto user = userService.loadUserByUserName(username);
        return new UserPrincipal(user);
    }
}
