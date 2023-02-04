package br.com.carv.restful.security;

import br.com.carv.restful.exception.InvalidJWTAuthenticationException;
import br.com.carv.restful.model.dto.response.UserResponse;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Service
public class JwtTokenProvider {

    @Value("${security.jwt.secret}")
    private String secretKey;

    @Value("${security.jwt.expiration-in-ms}")
    private Long validityInMilliseconds;

    private UserDetailsService userDetailsService;

    private Algorithm algorithm;

    public JwtTokenProvider(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
        this.algorithm = null;
    }

    @PostConstruct
    protected void init() {
        this.secretKey = Base64.getEncoder().encodeToString(this.secretKey.getBytes());
        this.algorithm = Algorithm.HMAC256(this.secretKey.getBytes());
    }

    public UserResponse createAccessToken(String username, List<String> roles) {
        LocalDateTime validity = LocalDateTime.now().plusHours(this.validityInMilliseconds);
        String accessToken = getAccessToken(username, roles, LocalDateTime.now(), validity);
        String refreshToken = getRefreshAccessToken(username, roles, LocalDateTime.now());
        return new UserResponse(username, true, LocalDateTime.now(), validity, accessToken, refreshToken);
    }

    public String getAccessToken(String username, List<String> roles, LocalDateTime now, LocalDateTime validity) {
        String issuerUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
        return JWT.create().withClaim("roles", roles)
                .withIssuedAt(Instant.from(now)).withExpiresAt(Instant.from(validity))
                .withSubject(username).withIssuer(issuerUrl).sign(algorithm).strip();
    }

    public String getRefreshAccessToken(String username, List<String> roles, LocalDateTime now) {
        LocalDateTime refreshToken = LocalDateTime.now().plusHours(this.validityInMilliseconds * 3);
        return JWT.create().withClaim("roles", roles)
                .withIssuedAt(Instant.from(now)).withExpiresAt(Instant.from(refreshToken))
                .withSubject(username).sign(algorithm).strip();
    }

    public Authentication getAuthentication(String token) {
        DecodedJWT decodedJWT = decodedToken(token);
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(decodedJWT.getSubject());
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    private DecodedJWT decodedToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(this.secretKey.getBytes());
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(token);
        return decodedJWT;
    }

    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if(bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring("Bearer ".length());
        }

        return null;
    }

    public Boolean validateToken(String token) {
        DecodedJWT decodedJWT = decodedToken(token);
        try {
            if(decodedJWT.getExpiresAt().before(new Date())) {
                return false;
            }
            return true;

        } catch (Exception exception) {
            throw new InvalidJWTAuthenticationException("Expired or invalid token!");
        }
    }

}
