//package jungle_week13.jungle_week13.config;
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//
///**
// * Spring Security 필터 체인에 JWT 필터를 추가하여,
// * 모든 요청에서 토큰을 검증하고 유효한 경우 인증된 사용자인지 확인.
// */
//@RequiredArgsConstructor
//public class JwtTokenFilter extends OncePerRequestFilter {
//
//    private JwtProvider jwtProvider;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request,
//                                    HttpServletResponse response,
//                                    FilterChain filterChain) throws ServletException, IOException {
//        String token = resolveToken(request);
//
//        // Spring Security의 인증 객체 생성 및 설정
//        if(token != null && jwtProvider.validateToken(token)) {
//            String userInfo = jwtProvider.getUserInfo(token);
//            UsernamePasswordAuthenticationToken authentication =
//                    new UsernamePasswordAuthenticationToken(userInfo, null, userInfo);
//        }
//    }
//
//    // 토큰 추출하기
//    private String resolveToken(HttpServletRequest request) {
//        String bearerToken = request.getHeader("Authorization");
//        if(bearerToken != null && bearerToken.startsWith("Bearer ")) {
//            return bearerToken.substring(7);
//        }
//        return null;
//    }
//}
