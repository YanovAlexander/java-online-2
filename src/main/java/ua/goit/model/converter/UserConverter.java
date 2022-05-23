package ua.goit.model.converter;

import org.springframework.stereotype.Service;
import ua.goit.model.dao.UserDao;
import ua.goit.model.dto.UserDto;

@Service
public class UserConverter implements Converter<UserDao, UserDto> {

    @Override
    public UserDto from(UserDao type) {
        UserDto dto = new UserDto();
        dto.setId(type.getId());
        dto.setFirstName(type.getFirstName());
        dto.setLastName(type.getLastName());
        dto.setUserName(type.getUserName());
        dto.setUserRole(type.getUserRole());
        dto.setUserStatus(type.getUserStatus());
        dto.setPassword(type.getPassword());
        return dto;
    }

    @Override
    public UserDao to(UserDto type) {
        UserDao dao = new UserDao();
        dao.setId(type.getId());
        dao.setFirstName(type.getFirstName());
        dao.setLastName(type.getLastName());
        dao.setUserName(type.getUserName());
        dao.setUserStatus(type.getUserStatus());
        dao.setUserRole(type.getUserRole());
        dao.setPassword(type.getPassword());
        return dao  ;
    }
}
