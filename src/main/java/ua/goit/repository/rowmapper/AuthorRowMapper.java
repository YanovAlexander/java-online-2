package ua.goit.repository.rowmapper;

import org.springframework.jdbc.core.RowMapper;
import ua.goit.model.dao.AuthorDao;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorRowMapper implements RowMapper<AuthorDao> {

    @Override
    public AuthorDao mapRow(ResultSet rs, int rowNum) throws SQLException {
        AuthorDao author = new AuthorDao();
        author.setId(rs.getInt("id"));
        author.setFirstName(rs.getString("first_name"));
        author.setLastName(rs.getString("last_name"));
        author.setEmail(rs.getString("email"));
        return author;
    }
}
