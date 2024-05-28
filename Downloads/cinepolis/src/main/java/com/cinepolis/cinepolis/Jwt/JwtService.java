 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cinepolis.cinepolis.Jwt;

import com.cinepolis.cinepolis.User.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import java.security.Key;
import io.jsonwebtoken.io.Decoders;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.security.Keys;
import java.util.function.Function;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;

/**
 *
 * @author admin
 */

@Service
public class JwtService {
    
    @Value("${jwt.secret}")
    private String SECRET_KEY;
      public String getToken(User user) {
        return getToken(new HashMap<>(), user);
    }
      
    private String getToken(Map<String,Object> extraClaims, User user) {
        return Jwts
            .builder()
            .claims(extraClaims)
            .claim("userId", user.getId())
            .claim("nombre", user.getNombre())
            .claim("apellidos", user.getApellidos())
            .claim("fechaNacimiento", user.getFechaNacimiento())
            .claim("codigoPostal", user.getCodigoPostal())
            .claim("municipio", user.getMunicipio())
            .claim("ciudad", user.getCiudad())
            .claim("pais", user.getPais())
            .claim("email", user.getEmail())
            .claim("telefono", user.getTelefono())
            .subject(user.getUsername())
            .issuedAt(new Date(System.currentTimeMillis()))
            .expiration(new Date(System.currentTimeMillis()+1000*60*24))
            .signWith(getKey())
            .compact();
    }
    
    private SecretKey getKey() {
       byte[] keyBytes=Decoders.BASE64.decode(SECRET_KEY);
       return Keys.hmacShaKeyFor(keyBytes);
    }
    
    public String getUsernameFromToken(String token) {
        return getClaim(token, Claims::getSubject);
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username=getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername())&& !isTokenExpired(token));
    }

    private Claims getAllClaims(String token)
    {
        return Jwts
            .parser()
            .verifyWith(getKey())
            .build()
            .parseSignedClaims(token)
            .getPayload();
    }

    public <T> T getClaim(String token, Function<Claims,T> claimsResolver)
    {
        final Claims claims=getAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Date getExpiration(String token)
    {
        return getClaim(token, Claims::getExpiration);
    }

    private boolean isTokenExpired(String token)
    {
        return getExpiration(token).before(new Date());
    }
}
