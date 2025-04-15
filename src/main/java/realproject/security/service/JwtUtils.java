package realproject.security.service;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import realproject.security.dto.TokenDecodeDto;

import javax.crypto.SecretKey;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JwtUtils {

    public String generateToken(UserDetails userDetails) {
        String roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(","));
        return Jwts.builder()
                .subject(userDetails.getUsername())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000  * 60 * 60 * 24 ))
                .claim("roles", roles)
                .signWith(getSecretKey()).compact();
    }

    public String generateRefreshToken(UserDetails userDetails) {
        String roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(","));
        return Jwts.builder()
                .subject(userDetails.getUsername())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7))
                .claim("roles", roles)
                .signWith(getSecretKey()).compact();
    }




    public boolean isValid(String token) {
        Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return true;
    }

    public SecretKey getSecretKey() {
        byte[] bytes = Decoders.BASE64.decode("1234567890123456789012345678901234567890123456789012345678901234");
        return Keys.hmacShaKeyFor(bytes);
    }

    public TokenDecodeDto getData(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
        String roles = claims.get("roles", String.class);
        List<SimpleGrantedAuthority> list = Arrays.stream(roles.split(",")).map(SimpleGrantedAuthority::new).toList();

        return new TokenDecodeDto(claims.getSubject(), list);
    }
}
