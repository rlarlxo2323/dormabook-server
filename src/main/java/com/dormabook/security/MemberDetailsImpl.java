package com.dormabook.security;

import com.dormabook.domain.member.Member;
import com.dormabook.model.Role;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@AllArgsConstructor
public class MemberDetailsImpl implements UserDetails {
    private static final String ROLE_PREFIX = "ROLE_";
    private final Member member;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        Role memberPermission = member.getMemberPermission();
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(ROLE_PREFIX + memberPermission.toString());
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(authority);

        return authorities;
    }

    @Override
    public String getPassword() {
        return member.getMemberPwd();
    }

    @Override
    public String getUsername() {
        return member.getMemberId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    //사용자 이름
    public String getMemberName(){
        return member.getMemberName();
    }

    public String getMemberStudentId(){
        return member.getMemberStudentId();
    }

    public String getMemberPhone(){
        return member.getMemberPhone();
    }

    public String getMemberMajor(){
        return member.getMemberMajor();
    }

    public String getMemberCollege(){
        return member.getMemberCollege();
    }

    public String getMemberIntroduce(){
        return member.getMemberIntroduce();
    }

    public int getMemberPoint(){
        return member.getMemberPoint();
    }
}
