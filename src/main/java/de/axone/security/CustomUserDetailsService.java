package de.axone.security;

import de.axone.model.User;
import de.axone.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;


@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        // Let user login with either username or email
        User userByUsernameOrEmail = userService.getUserByUsernameOrEmail(usernameOrEmail);
        return UserPrincipal.create(userByUsernameOrEmail);
    }

    // This method is used by JWTAuthenticationFilter
    @Transactional
    public UserDetails loadUserById(Long id) {
        User user = userService.getUserById(id);
        return UserPrincipal.create(user);
    }
}
