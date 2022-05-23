package ua.goit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.goit.exception.UserAlreadyExistsException;
import ua.goit.model.User;
import ua.goit.model.UserRole;
import ua.goit.model.UserStatus;
import ua.goit.repository.UserRepository;

@Service
public class UserService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerUser(User user) {
        if (emailExists(user.getEmail())) {
            throw new UserAlreadyExistsException("There is an account with that email address: " + user.getEmail());
        }
        user.setUserRole(UserRole.ROLE_NEWCOMER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setStatus(UserStatus.ACTIVE);
        userRepository.save(user);
    }

    private boolean emailExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }
}
