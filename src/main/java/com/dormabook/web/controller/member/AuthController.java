package com.dormabook.web.controller.member;

import com.dormabook.service.member.AuthService;
import com.dormabook.web.dto.member.JwtRequestDto;
import com.dormabook.web.dto.member.JwtResponseDto;
import com.dormabook.web.dto.member.MemberIdRequestDto;
import com.dormabook.web.dto.member.MemberSignupRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping(value = "signup")
    public String signup(@RequestBody MemberSignupRequestDto request){
        return authService.signup(request);
    }

    @PostMapping(value = "signup/checkid")
    public String checkId(@RequestBody MemberIdRequestDto request){
        return authService.checkId(request);
    }

    @PostMapping(value = "login", produces = MediaType.APPLICATION_JSON_VALUE)
    public JwtResponseDto login(@RequestBody JwtRequestDto request) {
        try {
            return authService.login(request);
        } catch (Exception e) {
            return new JwtResponseDto(e.getMessage());
        }
    }
}
