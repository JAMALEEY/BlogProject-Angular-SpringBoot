package ma.youcode.firo.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.security.Key;

@Service
public class JwtProvider {

    // in order to avoid creating a new key every time we generate the key (create the key once)
    private Key key;
    // in order to allow the key to be created on server startup (and use the same key when generated the jwt)
    @PostConstruct
    public void init() {
        key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    }

    public String generateToken(Authentication authentication){
        // casting to user of userDetail (springSecurity)
        User principal = (User) authentication.getPrincipal();
        return Jwts.builder().setSubject(principal.getUsername())
                // taking the key and sign it with the jwt
                .signWith(key)
                .compact();
    }
}
