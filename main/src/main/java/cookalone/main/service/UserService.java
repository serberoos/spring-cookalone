package cookalone.main.service;

import cookalone.main.domain.User;
import cookalone.main.domain.dto.account.UserRequestDto;

import java.util.List;

public interface UserService {
    public Long join(UserRequestDto userDto);
    public List<User> findUsers();
}
