package de.axone.service;

import de.axone.model.RefreshToken;
import de.axone.repository.RefreshTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenService {

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private UserService userService;

    public RefreshToken createRefreshToken(String username) {
        RefreshToken refreshToken = RefreshToken.builder()
                .userId(userService.getUserByUsernameOrEmail(username))
                .token(UUID.randomUUID().toString())
                .expiryDate(Instant.now().plusMillis(1800000))//30 minutes
                .build();

        return refreshTokenRepository.saveAndFlush(refreshToken);
    }

    public RefreshToken getToken(String token) {
        Optional<RefreshToken> refreshToken = refreshTokenRepository.findRefreshTokenByToken(token);
        return refreshToken.orElse(null);
    }

    public RefreshToken verifyRefreshToken(RefreshToken refreshToken) {
        if (refreshToken.getExpiryDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(refreshToken);
            throw new RuntimeException(refreshToken.getToken() + " Refresh token was expired, please login again!");
        }
        return refreshToken;
    }


}
