package ua.goit.repository;

import org.springframework.data.repository.CrudRepository;
import ua.goit.model.dao.UserDao;

import java.util.Optional;

public interface UserRepository extends CrudRepository<UserDao, Integer> {

    Optional<UserDao> findByUserName(String userName);
}
