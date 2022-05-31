//package com.dormabook.web.dto.member;
//
//import com.dormabook.domain.member.Member;
//import lombok.Builder;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//
//@Getter
//@NoArgsConstructor
//public class MemberRequestDto {
//    private String memberId;
//    private String memberPwd;
//    private String memberName;
//    private String memberStudentId;
//    private String memberPhone;
//    private String memberMajor;
//    private String memberCollege;
//    private String memberIntroduce;
//    private int memberPoint;
//
//    public MemberRequestDto(String memberId, String memberPwd, String memberName, String memberStudentId, String memberPhone, String memberMajor, String memberCollege, String memberIntroduce, int memberPoint) {
//        this.memberId = memberId;
//        this.memberPwd = memberPwd;
//        this.memberName = memberName;
//        this.memberStudentId = memberStudentId;
//        this.memberPhone = memberPhone;
//        this.memberMajor = memberMajor;
//        this.memberCollege = memberCollege;
//        this.memberIntroduce = memberIntroduce;
//        this.memberPoint = memberPoint;
//    }
//
////    @Builder
////    public Member toEntity(){
////        return Member.builder()
////                .memberId(memberId)
////                .memberPwd(memberPwd)
////                .memberName(memberName)
////                .memberStudentId(memberStudentId)
////                .memberPhone(memberPhone)
////                .memberMajor(memberMajor)
////                .memberCollege(memberCollege)
////                .memberIntroduce(memberIntroduce)
////                .memberPoint(memberPoint)
////                .build();
////    }
//}
