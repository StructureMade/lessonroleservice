package de.structuremade.ms.lessonservice.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.function.Function;


@Service
public class JWTUtil {

/*    private final List<String> blacklistMap = new ArrayList<>();

    @Bean
    private void blacklistedTokenSort() {
        if (!blacklistMap.isEmpty()) {
            for (int i = 0; i <= blacklistMap.size(); i++) {
                if (extractExpiration(blacklistMap.get(i)).before(new Date())) {
                    blacklistMap.remove(i);
                }
            }
        }
    }

    public boolean isTokenInBlacklist(String token) {
        return blacklistMap.contains(token);
    }

    public void addBlacklistedToken(String token) {
        blacklistMap.add(token);
    }*/

    public String extractIdOrEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        Claims claims;
        claims = extracAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String extractSpecialClaim(String jwt, String claim) {
        Claims claims;
        claims = extracAllClaims(jwt);
        return String.valueOf(claims.get(claim));
    }

    private Claims extracAllClaims(String token) {
        return Jwts.parser().setSigningKey("").parseClaimsJws(token).getBody();
    }

    public Boolean isTokenExpired(String token) {
        try {
            this.extractIdOrEmail(token);
            return false;
        } catch (Exception e) {
            return true;
        }
    }
}
