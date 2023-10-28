package de.axone.controller;

import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import de.axone.model.User;
import de.axone.payload.*;
import de.axone.security.JwtTokenProvider;
import de.axone.service.*;

import de.axone.utils.HeaderUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Map;


@RestController
@RequestMapping("tachy/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private AuthService authService;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsernameOrEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return ResponseEntity.ok(authService.createAuthenticationResponse(authentication, loginRequest.getUsernameOrEmail()));
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

        User user = authService.registerUser(signUpRequest);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/tachy/users/{username}")
                .buildAndExpand(user.getUsername()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(
                true,
                "User successfully created!"
        ));
    }

    @PostMapping("/refreshToken")
    public ResponseEntity<?> refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        return ResponseEntity.ok(authService.refreshToken(refreshTokenRequest));
    }

    @PostMapping("/signout")
    public ResponseEntity<?> logoutUser(HttpServletRequest request) {
        String jwt = HeaderUtils.parseJwt(request);
        DecodedJWT decodedJwt = jwtTokenProvider.decode(jwt);
        Map<String, Claim> claims = decodedJwt.getClaims();
        claims.clear();

        return ResponseEntity.ok(new ApiResponse(true, "The token is expired!"));
    }
}
