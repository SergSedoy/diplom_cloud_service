package ru.netology.diplom_cloud_service.security;

import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Component
public class JwtProvider {
    private static final Logger LOG = LoggerFactory.getLogger(JwtProvider.class);
    private final String jwtSecret = "jwtDiploma";

    public String generateToken(String login) {
        Date date = Date.from(LocalDate.now().plusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant());
        return Jwts.builder()
                .setSubject(login)
                .setExpiration(date)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException expEx) {
            LOG.info("Token expired");
        } catch (UnsupportedJwtException unsEx) {
            LOG.info("Unsupported jwt");
        } catch (MalformedJwtException mjEx) {
            LOG.info("Malformed jwt");
        } catch (SignatureException sEx) {
            LOG.info("Invalid signature");
        } catch (Exception e) {
            LOG.info("invalid token");
        }
        return false;
    }

    public String getLoginFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }
}
