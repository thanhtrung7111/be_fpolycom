package service.common;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JWTService {

    @Autowired
    EncodingService encodingService;

    public static final String SECRECT_KEY = "qazwsxedcrfvtgbyhnujmikolpplokmijnuhbygvtfcrdxeszwaqfpolycom";


    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, username);
    }

    private String createToken(Map<String, Object> claims, String username) {
        return Jwts.builder().setClaims(claims).setSubject(encodingService.encode(username)).setIssuedAt(new Date()).setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30)).signWith(getSignKey()).compact();
    }

    private Key getSignKey(){
        byte[] keyBytes = Decoders.BASE64.decode(SECRECT_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String extractUsername(String token){
        return encodingService.decode(extractClaim(token,Claims::getSubject)).split("&")[0];
    }

    public String extractRoleLogin(String token){
        return encodingService.decode(extractClaim(token,Claims::getSubject)).split("&")[1];
    }

    public Date extractExpiration(String token){
        return extractClaim(token,Claims::getExpiration);
    }


    public <T> T extractClaim(String token, Function<Claims,T> claimResolver){
        final Claims claims = extractAllClaim(token);
        return claimResolver.apply(claims);
    }

    private Claims extractAllClaim(String token){
        return Jwts.parser().setSigningKey(getSignKey()).build().parseClaimsJws(token).getBody();
    }

    public Boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }


    public Boolean validateToken(String token, UserDetails userDetails){
        final String[] username = extractUsername(token).split("&");
        return (username[0].equals(userDetails.getUsername()) && !isTokenExpired(token));
    }


}
