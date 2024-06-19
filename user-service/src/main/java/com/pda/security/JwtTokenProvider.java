package com.pda.security;

import com.pda.utils.api_utils.CustomStringUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.util.StringUtils;

import java.security.Key;
import java.util.Base64;
import java.util.Collection;
import java.util.Date;

@Slf4j
@PropertySource(value = {"env.properties"})
public class JwtTokenProvider {


    // 환경변수로 설정해서 불러오기


    @Value("${jwt.SECRET_KEY}")
    private String SECRET_KEY;
    private Key key;
    private long tokenValidTime = 24 * 60 * 60 * 1000L; // 24 hours

    @Autowired
    private UserDetailsService userDetailsService;


    // 시크릿 키 초기화
    @PostConstruct
    protected void init() {
//        key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    // 토큰 생성
    public String createToken(String username) {
        Claims claims = Jwts.claims().setSubject(username);
        Date now = new Date();
        log.info("createToken - username : " + username);

        // header에 들어갈 내용 및 서명을 하기 위한 SECRET_KEY
        // payload에 들어갈 내용
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + tokenValidTime))
                .signWith(key)
                .compact();
    }

    // 헤더에서 토큰 얻기
    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        return CustomStringUtils.getToken(bearerToken);
    }


    // 내가 만든 토큰인지 & username 기반으로 DB 에서 객체 가져오기
    public Authentication getAuthentication(String token) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(getUsername(token));
        log.info("auth - " + userDetails.getUsername());
        return new UsernamePasswordAuthenticationToken(userDetails, "",
                userDetails.getAuthorities());
    }


    // 토큰에서 username 가져오기
    public String getUsername(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build()
                .parseClaimsJws(token).getBody().getSubject();
    }
}
