package ua.goit.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.goit.model.dao.AuthorDao;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface AuthorRepository extends CrudRepository<AuthorDao, Integer> {
    Optional<AuthorDao> findByEmail(String email);
    @Query("SELECT author FROM AuthorDao author WHERE author.id IN (?1)")
    List<AuthorDao> findByIds(Set<Integer> ids);
}
