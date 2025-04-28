package ut.edu.koii.service;

import ut.edu.koii.models.User;
import ut.edu.koii.models.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAllUsers();
    Optional<User> getUserById(Long id);
    Optional<User> getUserByUsername(String username);
    User saveUser(User user);
    void deleteUserById(Long id);

    // Thêm phương thức đăng ký
    boolean registerNewUser(UserDto userDto);
}


