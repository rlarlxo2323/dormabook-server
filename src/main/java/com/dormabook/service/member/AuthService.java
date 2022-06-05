package com.dormabook.service.member;

import com.dormabook.domain.member.Member;
import com.dormabook.domain.member.MemberRepository;
import com.dormabook.security.JwtTokenProvider;
import com.dormabook.security.MemberDetailsImpl;
import com.dormabook.web.dto.member.*;
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
        if (existMember) {
            return "exist";
        }else {
            Member member = new Member(request);
            member.encryptPassword(passwordEncoder);

            memberRepository.save(member);
            return member.getMemberId();
        }
    }

    public String checkId(MemberIdRequestDto request){
        boolean existMember = memberRepository.existsById(request.getMemberId());

        // 이미 회원이 존재하는 경우
        if (existMember) {
            return "exist";
        } else {
            return null;
        }
    }

    public String searchId(MemberSearchRequestDto request){

        return memberRepository.findIdByName(request.getMemberName(), request.getMemberPhone());
    }

    public String searchPw(MemberSearchRequestDto request){

        return memberRepository.findPwById(request.getMemberName(), request.getMemberPhone(), request.getMemberId());
    }

    public int modifyPw(JwtRequestDto request){

        String encodePwd = passwordEncoder.encode(request.getMemberPwd());

        return memberRepository.modifyPwById(request.getMemberId(), encodePwd);
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
