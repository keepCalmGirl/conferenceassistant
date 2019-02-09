package ifit.cluster.cassistant.service;

import ifit.cluster.cassistant.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> getUserById(Long id);
    Optional<User> getUserByEmail(String email);
    List<User> getAllUsers();
    User saveUser(User user);
}
