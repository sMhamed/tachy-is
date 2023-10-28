package de.axone.service;

import de.axone.enums.RoleType;
import de.axone.model.RefreshToken;
import de.axone.model.Role;
import de.axone.model.User;
import de.axone.payload.JwtAuthenticationResponse;
import de.axone.payload.RefreshTokenRequest;
import de.axone.payload.SignUpRequest;
import de.axone.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;


@Service
public class AuthService {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private RefreshTokenService refreshTokenService;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public JwtAuthenticationResponse createAuthenticationResponse(Authentication authentication, String usernameOrEmail) {

        RefreshToken refreshToken = refreshTokenService.createRefreshToken(usernameOrEmail);
        String jwt = jwtTokenProvider.generateToken(authentication);
        Long userId = jwtTokenProvider.getUserIdFromJWT(jwt);
        String usernameClaim = jwtTokenProvider.getUsernameFromJWT(jwt);
        String emailClaim = jwtTokenProvider.getEmailFromJWT(jwt);
        String rolesClaim = jwtTokenProvider.getRolesFromJWT(jwt);
        return new JwtAuthenticationResponse(jwt, refreshToken.getToken(), userId, emailClaim, usernameClaim, rolesClaim);
    }


    public User registerUser(SignUpRequest signUpRequest) {

        User user = new User(
                signUpRequest.getFirstName(),
                signUpRequest.getLastName(),
                signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                signUpRequest.getPassword());

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Role userRole = roleService.getRoleByName(RoleType.ROLE_USER);

        user.setRoles(Collections.singleton(userRole));

        return userService.createUser(user);
    }

    public JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        RefreshToken refreshToken = refreshTokenService.getToken(refreshTokenRequest.getToken());
        RefreshToken verifiedRefreshToken = refreshTokenService.verifyRefreshToken(refreshToken);
        User user = userService.getUserById(verifiedRefreshToken.getUserId().getId());
        String jwt = jwtTokenProvider.generateToken(user);
        Long userId = jwtTokenProvider.getUserIdFromJWT(jwt);
        String usernameClaim = jwtTokenProvider.getUsernameFromJWT(jwt);
        String emailClaim = jwtTokenProvider.getEmailFromJWT(jwt);
        String rolesClaim = jwtTokenProvider.getRolesFromJWT(jwt);
        return new JwtAuthenticationResponse(jwt, refreshToken.getToken(), userId, emailClaim, usernameClaim, rolesClaim);
    }


}
