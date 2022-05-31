package com.dormabook.security;

import com.dormabook.model.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.val;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Component
public class JwtTokenProvider {

    @Value("${jwt.secret}")
    private String secretKey;

    // 토큰 유효시간 30분
    @Value("${jwt.access-expired}")
    private long tokenValidTime;

    private final MemberDetailsServiceImpl memberDetailsService;

    // 객체 초기화, secretKey를 Base64로 인코딩
    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String generateToken(MemberDetailsImpl memberDetails) {

        Map<String, Object> claims = new HashMap<>();

        val isAdmin = memberDetails.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_"+ Role.ADMIN));
        if (isAdmin) {
            claims.put("memberPermission","admin");
        } else {
            claims.put("memberPermission","user");
        }

        val memberName = memberDetails.getMemberName();
        claims.put("memberName", memberName);

        val memberPoint = memberDetails.getMemberPoint();
        claims.put("memberPoint", memberPoint);

        val memberMajor = memberDetails.getMemberMajor();
        claims.put("memberMajor", memberMajor);

        val memberPhone = memberDetails.getMemberPhone();
        claims.put("memberPhone", memberPhone);

        val memberCollege = memberDetails.getMemberCollege();
        claims.put("memberCollege", memberCollege);

        val memberIntroduce = memberDetails.getMemberIntroduce();
        claims.put("memberIntroduce", memberIntroduce);

        val memberStudentId = memberDetails.getMemberStudentId();
        claims.put("memberStudentId", memberStudentId);

        return doGenerateToken(claims, memberDetails.getUsername());
    }

    private String doGenerateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setHeaderParam("typ","JWT")
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + tokenValidTime * 1000))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    // JWT 토큰에서 인증 정보 조회
    public Authentication getAuthentication(String token) {
        UserDetails userDetails = memberDetailsService.loadUserByUsername(this.getUserPk(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    // 토큰에서 회원 정보 추출
    public String getUserPk(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    // Request의 Header에서 token 값을 가져옴.
    public String resolveToken(HttpServletRequest request) {
        return request.getHeader(HttpHeaders.AUTHORIZATION);
    }

    // 토큰의 유효성 + 만료일자 확인
    public boolean validateToken(String jwtToken) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }
}
