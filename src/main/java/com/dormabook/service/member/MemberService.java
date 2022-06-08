package com.dormabook.service.member;

import com.dormabook.domain.member.Member;
import com.dormabook.domain.member.MemberRepository;
//import com.dormabook.web.dto.member.MemberResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public Optional<Member> getInfo(String id){
        return memberRepository.findById(id);
    }
}
