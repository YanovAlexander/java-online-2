package ua.goit.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.goit.model.dao.AuthorDao;

import java.util.Optional;
import java.util.Set;

@Repository
public interface AuthorRepository extends CrudRepository<AuthorDao, Integer>{

    Optional<AuthorDao> findByEmail(String email);
    Optional<AuthorDao> findByName(String name);
    Set<AuthorDao> findByIds(Set<Integer> ids);
}
