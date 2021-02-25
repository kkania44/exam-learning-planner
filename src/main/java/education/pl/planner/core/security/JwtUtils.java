package education.pl.planner.core.security;

import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;

@Component
public class JwtUtils {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${planner.app.jwtSecret}")
    private String jwtSecret;
    @Value("${planner.app.jwtExpirationMillis}")
    private int jwtExpirationMillis;

    public String generateJwtToken(Authentication authentication) {
        User principal = (User) authentication.getPrincipal();

        String token = Jwts.builder()
                .setSubject(principal.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(Date.from(Instant.now().plusMillis(jwtExpirationMillis)))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
        logger.info("Generated token : {}", token);
        return token;
    }

    public String getUsernameFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret)
                .parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (SignatureException e) {
            logger.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
        }

        return false;
    }
}
