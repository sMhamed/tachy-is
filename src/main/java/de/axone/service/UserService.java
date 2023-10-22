package de.axone.service;

import de.axone.model.Customer;
import de.axone.model.User;
import de.axone.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElse(null);
    }

    public User getUserByUsernameOrEmail(String usernameOrEmail) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findUserByUsernameOrEmail(usernameOrEmail, usernameOrEmail);
        return user.orElseThrow(() ->
                new UsernameNotFoundException("User not found with username or email : " + usernameOrEmail));
    }

    public Boolean isUserExistByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public Boolean isUserExistByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public User createUser(User user) {
        return userRepository.saveAndFlush(user);
    }

}
