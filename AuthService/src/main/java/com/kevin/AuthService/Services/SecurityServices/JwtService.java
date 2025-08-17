package com.kevin.AuthService.Services.SecurityServices;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    private final static String SECRET_KEY="3F7A9F1C8D2E5B4A2F6E3C7B8A9D7E5F3A1E7C8B5F2A9D8E6C3B5F4A9E7C2D1";

    public Claims extractClaims(String token){
        return Jwts
                .parser()
                .verifyWith((SecretKey) getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();

    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }


    public String generateJwtTokenWithUserDetails(UserDetails userDetails){
        return generateJwtToken(new HashMap<>(),userDetails);
    }

    public String generateJwtToken(
            Map<String ,Object> extraClaim,
            UserDetails userDetails
    ){
        return Jwts
                .builder()
                .claims(extraClaim)
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+8*60*60*1000))
                .signWith(getSignInKey())
                .compact();
    }

    public boolean isValidToken(String token,UserDetails userdetail){

        final String userId= extractUsername(token);
        return (userId.equals(userdetail.getUsername())) && !isNotExpired(token);

    }


    public boolean isNotExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token){
        return extractNeededClaim(token,Claims :: getExpiration);
    }

    public String extractUsername (String token){
        return extractNeededClaim(token , Claims :: getSubject);
    }


    public <T> T extractNeededClaim(String token , Function<Claims,T> claimResolver){
        final Claims claim = extractClaims(token);
        return claimResolver.apply(claim);

    }
}

