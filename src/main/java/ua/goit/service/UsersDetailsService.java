package ua.goit.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ua.goit.model.User;
import ua.goit.model.auth.UserPrincipal;
import ua.goit.repository.UserRepository;

public class UsersDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    public UsersDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final User user = userRepository.findByEmail(username).orElseThrow(() ->
                new UsernameNotFoundException(String.format("user with username %s not exists", username)));
        return new UserPrincipal(user);
    }
}
