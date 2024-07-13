package com.example.M2S07.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TokenService {
    private final JwtEncoder jwtEncoder;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtDecoder jwtDecoder;
    public String gerarToken(Authentication authentication) {
        Instant now = Instant.now();
        String scope = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(" "));
        JwtClaimsSet claims = JwtClaimsSet.builder().issuer("self").issuedAt(now).expiresAt(now.plus(10, ChronoUnit.HOURS)).subject(authentication.getName()).claim("scope", scope).build();
        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    public boolean validaToken(String token, String perfil) {
        System.out.println("Token: " + token);
        String tokenReal = token.split(" ")[1];
        System.out.println("Token real: " + tokenReal);
        System.out.println(jwtDecoder.decode(tokenReal).getClaim("sub").toString());
        List<String> perfisToken = List.of(jwtDecoder.decode(tokenReal).getClaim("scope").toString().split(" "));
        return perfisToken.contains(perfil);
    }
}
