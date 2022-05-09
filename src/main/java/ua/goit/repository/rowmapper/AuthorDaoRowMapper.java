package ua.goit.repository.rowmapper;

import org.springframework.jdbc.core.RowMapper;
import ua.goit.model.dao.AuthorDao;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorDaoRowMapper implements RowMapper<AuthorDao> {
    @Override
    public AuthorDao mapRow(ResultSet rs, int rowNum) throws SQLException {
        AuthorDao authorDao = new AuthorDao();
        authorDao.setId(rs.getInt("id"));
        authorDao.setFirstName(rs.getString("first_name"));
        authorDao.setLastName(rs.getString("last_name"));
        authorDao.setEmail(rs.getString("email"));
        return authorDao;
    }
}
