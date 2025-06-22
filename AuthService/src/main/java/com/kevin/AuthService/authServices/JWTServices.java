package com.ScarZeus.recipe_backend.service.authServices;


import com.ScarZeus.recipe_backend.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class JWTServices {

    private final UserService userService;

    private final String secretKey="3F7A9F1C8D2E5B4A2F6E3C7B8A9D7E5F3A1E7C8B5F2A9D8E6C3B5F4A9E7C2D1";

    public String  getJwtToken(Map<String,Object> extraClaims , UserDetails userDetails) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+8*60*60*1000))
                .signWith(SignatureAlgorithm.HS256,getSignInKey())
                .compact();
    }

    public String getJwtToken(UserDetails userDetails){
        return getJwtToken(new HashMap<>(),userDetails);
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }



    public String extractEmailFromJwt(String jwtToken) {
        return extractRequestedClaims(jwtToken, Claims::getSubject);
    }

    public String extractExpiryFromJwt(String jwtToken){
        return extractRequestedClaims(jwtToken,Claims::getSubject);
    }

    public <T> T extractRequestedClaims(String jwtToken , Function<Claims,T> claimResolver){
        final Claims claims = extractClaims(jwtToken);
        return claimResolver.apply(claims);
    }

    private Claims extractClaims(String jwtToken) {
        return Jwts
                .parser()
                .verifyWith((SecretKey) getSignInKey())
                .build()
                .parseSignedClaims(jwtToken)
                .getPayload();
    }

    private Date extractExpiration(String jwtToken){
        return extractRequestedClaims(jwtToken,Claims::getExpiration);
    }

    private boolean isTokenExpired(String jwtToken) {
        Date expiryDate = extractExpiration(jwtToken);
        return  expiryDate.before(new Date());
    }

    public boolean isValidToken(String jwtToken, UserDetails userDetails) {
        String extractedUserName = extractEmailFromJwt(jwtToken);
        return userService.isUserExist(extractEmailFromJwt(jwtToken)) && !isTokenExpired(jwtToken);
    }
}