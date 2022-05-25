package ua.goit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.goit.exception.UserAlreadyExistsException;
import ua.goit.model.UserRole;
import ua.goit.model.UserStatus;
import ua.goit.model.converter.UserConverter;
import ua.goit.model.dto.UserDto;
import ua.goit.repository.UserRepository;

@Service
public class UserService {
    private UserRepository userRepository;
    private UserConverter userConverter;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, UserConverter userConverter, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userConverter = userConverter;
        this.passwordEncoder = passwordEncoder;
    }

    public void save(UserDto user) {
        validateUser(user);

        user.setUserRole(UserRole.ROLE_VISITOR);
        user.setUserStatus(UserStatus.ACTIVE);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(userConverter.to(user));
    }

    private void validateUser(UserDto dto) {
        userRepository.findByUserName(dto.getUserName())
                .ifPresent((user) -> {throw new UserAlreadyExistsException("User with username " + user.getUserName() +
                        " already exists");});
    }

    public UserDto loadUserByUserName(String username) {
       return userConverter.from(userRepository.findByUserName(username).orElseThrow(() ->
               new UsernameNotFoundException(String.format("User with username %s not exists", username))));
    }
}
