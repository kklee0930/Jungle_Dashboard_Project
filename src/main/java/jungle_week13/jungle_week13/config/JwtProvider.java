//package jungle_week13.jungle_week13.config;
//import io.jsonwebtoken.*;
//import io.jsonwebtoken.security.Keys;
//import jakarta.annotation.PostConstruct;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.core.Authentication;
//import org.springframework.stereotype.Component;
//
//import java.nio.charset.StandardCharsets;
//import java.security.Key;
//import java.util.Date;
//import java.util.UUID;
//
///**
// * JWT 생성 및 파싱을 담당하는 유틸리티 클래스.
// * 이 클래스는 토큰 발급, 토큰에서 사용자 정보 추출, 토큰 유효성 검사 기능을 수행함.
// */
//@Component
//@RequiredArgsConstructor
//public class JwtProvider {
//
//    @Value("${jwt.secret.key}")
//    private String salt;
//
//    private Key secretKey;
//    private final long validityInMilliseconds = 3600000; // 만료시간 1h으로 설정
//
//    @PostConstruct
//    protected void init() {
//        secretKey = Keys.hmacShaKeyFor(salt.getBytes(StandardCharsets.UTF_8));
//    }
//
////    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256); // 키 생성
//
//    // 토큰 생성
//    public String createToken(UUID uuid, String email, String password, String username) {
//
//        Claims claims = Jwts.claims().setSubject(uuid.toString()); // 클레임 설정
//        claims.put("email", email);
//        claims.put("password", password);
//        claims.put("username", username);
//
//        Date now = new Date();
//        Date expiration = new Date(now.getTime() + validityInMilliseconds); // 토큰 만료 시간 설정
//
//        return Jwts.builder()
//                .setIssuedAt(now)
//                .setExpiration(expiration)
//                .setClaims(claims) // 클레임 설정
//                .signWith(secretKey, SignatureAlgorithm.HS256)
//                .compact();
//    }
//
//    // 권한 정보 획득
//    // Spring Security 인증과정에서 권한확인을 위한 기능
//    public Authentication getAuthentication(String token) {
//
//    }
//
//
//    // 토큰에서 사용자 정보 추출
//    public String getUserInfo(String token) {
//        return Jwts.parserBuilder().setSigningKey(key).build()
//                .parseClaimsJws(token)
//                .getBody()
//                .getSubject();
//    }
//
//    // 토큰 유효성 검사
//    public boolean validateToken(String token) {
//        try {
//            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
//            return true;
//        } catch (JwtException | IllegalArgumentException e) {
//            return false;
//        }
//    }
//}