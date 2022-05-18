package ua.goit.repository;

import org.springframework.data.repository.CrudRepository;
import ua.goit.model.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {

    Optional<User> findByEmail(String email);
}
