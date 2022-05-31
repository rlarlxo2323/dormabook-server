package com.dormabook.service.member;

import com.dormabook.domain.member.Member;
import com.dormabook.domain.member.MemberRepository;
import com.dormabook.security.JwtTokenProvider;
import com.dormabook.security.MemberDetailsImpl;
import com.dormabook.web.dto.member.JwtRequestDto;
import com.dormabook.web.dto.member.JwtResponseDto;
import com.dormabook.web.dto.member.MemberSignupRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
@AllArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    public String signup(MemberSignupRequestDto request) {
        boolean existMember = memberRepository.existsById(request.getMemberId());

        // 이미 회원이 존재하는 경우
        if (existMember) return null;

        Member member = new Member(request);
        member.encryptPassword(passwordEncoder);

        memberRepository.save(member);
        return member.getMemberId();
    }

    public JwtResponseDto login(JwtRequestDto request) throws Exception {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getMemberId(), request.getMemberPwd()));

        return createJwtToken(authentication);
    }

    private JwtResponseDto createJwtToken(Authentication authentication){
        MemberDetailsImpl principal = (MemberDetailsImpl)  authentication.getPrincipal();
        String token = jwtTokenProvider.generateToken(principal);
        return new JwtResponseDto(token);
    }



}
