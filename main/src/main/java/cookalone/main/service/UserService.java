package cookalone.main.service;

import cookalone.main.domain.User;
import cookalone.main.domain.dto.account.UserDto;

import java.util.List;

public interface UserService {
    public Long join(UserDto.Request userDto);
    public List<User> findUsers();
}
