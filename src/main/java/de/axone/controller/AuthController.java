package de.axone.controller;

import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import de.axone.enums.RoleType;
import de.axone.model.Role;
import de.axone.model.User;
import de.axone.payload.ApiResponse;
import de.axone.payload.JwtAuthenticationResponse;
import de.axone.payload.LoginRequest;
import de.axone.payload.SignUpRequest;
import de.axone.security.JwtTokenProvider;
import de.axone.service.RoleService;
import de.axone.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collections;
import java.util.List;


@RestController
@RequestMapping("tachy/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;


    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsernameOrEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtTokenProvider.generateToken(authentication);
        Long userId = jwtTokenProvider.getUserIdFromJWT(jwt);
        String usernameClaim = jwtTokenProvider.getUsernameFromJWT(jwt);
        String emailClaim = jwtTokenProvider.getEmailFromJWT(jwt);
        String rolesClaim = jwtTokenProvider.getRolesFromJWT(jwt);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt, userId, emailClaim, usernameClaim, rolesClaim));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {

        // Check if email already exist
        if (userService.isUserExistByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity<>(new ApiResponse(false, "This email already exist!"), HttpStatus.BAD_REQUEST);
        }

        // Check if username already exist
        if (userService.isUserExistByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity<>(new ApiResponse(false, "This username already exist!"), HttpStatus.BAD_REQUEST);
        }

        User user = new User(
                signUpRequest.getFirstName(),
                signUpRequest.getLastName(),
                signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                signUpRequest.getPassword());

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Role userRole = roleService.getRoleByName(RoleType.ROLE_INSTALLER);

        user.setRoles(Collections.singleton(userRole));

        User result = userService.createUser(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/tachy/users/{username}")
                .buildAndExpand(result.getUsername()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(
                true,
                "User successfully created!"
        ));
    }

//    @PostMapping("/signout")
//    public ResponseEntity<?> logoutUser(HttpServletRequest request) {
//        String jwt = HeaderUtils.parseJwt(request);
//        DecodedJWT decodedJwt = jwtTokenProvider.decode(jwt);
//        final Claim claim = decodedJwt.getClaim("")
//
//
////        jwtTokenProvider.putBlackSet(jwt);
//
//        return ResponseEntity.ok(new ApiResponse(true, "The token is expired!"));
//    }
}
