package com.dormabook.service.member;

import com.dormabook.domain.member.Member;
import com.dormabook.domain.member.MemberRepository;
//import com.dormabook.web.dto.member.MemberResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;

//    //사용자 로그인
//    public MemberResponseDto findByMemberid(String memberId, String memberPwd){
//        Member entity = memberRepository.findById(memberId)
//                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 아이디입니다."));
//
//        if(!entity.getMemberPwd().equals(memberPwd)){
//            return false;
//        }
//        return new MemberResponseDto(entity);
//    }
}
