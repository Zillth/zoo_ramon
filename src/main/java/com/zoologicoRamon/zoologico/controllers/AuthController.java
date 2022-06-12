package com.zoologicoRamon.zoologico.controllers;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zoologicoRamon.zoologico.database.MySQLConnection;
import com.zoologicoRamon.zoologico.models.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final MySQLConnection connection = new MySQLConnection();

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {

        String token = "";

        if(this.connection.isAdmin(user)) {
            token = getJWTToken(user.getUsername());
        } else {
            return new ResponseEntity<String>("No access", HttpStatus.FORBIDDEN);
        }

        if (token.length() > 0) {
            return new ResponseEntity<String>(token, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    private String getJWTToken(String username) {
        String secretKEY = "me llamo Ramon, RamonCarlosBoyerGarciaSanchezSantaMariaLaNiñaPinta";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");

        String token = Jwts.builder().setId("hemerotecaCatalogoJWT").setSubject(username)
                .claim("authorities",
                        grantedAuthorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 90000000))
                .signWith(SignatureAlgorithm.HS512, secretKEY.getBytes()).compact();

        return "Zoo " + token;
    }
}
