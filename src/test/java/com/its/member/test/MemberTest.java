package com.its.member.test;

import com.its.member.dto.MemberDTO;
import com.its.member.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
@SpringBootTest
public class MemberTest {

    @Autowired
    private MemberService memberService;

    public MemberDTO newMember() {
        MemberDTO memberDTO = new MemberDTO("테스트용이메일", "테스트용비밀번호", "테스트용이름", 99, "테스트용전화번호");
        return memberDTO;
    }

    @Test
    @Transactional // 테스트클래스에서 쓰면 자동으로 롤백됨.
    @Rollback(value = true)
    @DisplayName("회원가입 테스트")
    public void saveTest() {
//        MemberDTO memberDTO = new MemberDTO("aa", "aa", "aa", 10, "aa");
//        Long saveId = memberService.save(memberDTO);
        Long saveId = memberService.save(newMember());
        MemberDTO findDTO = memberService.findById(saveId);
        assertThat(newMember().getMemberEmail()).isEqualTo(findDTO.getMemberEmail());
    }

    @Test
    @Transactional
    @Rollback
    @DisplayName("로그인 테스트")
    public void loginTest() {
        final String memberEmail = "로그인용이메일";
        final String memberPassword = "로그인용비번";
        String memberName = "로그인용이름";
        int memberAge = 99;
        String memberPhone = "로그인용전화번호";
        MemberDTO memberDTO = new MemberDTO(memberEmail, memberPassword, memberName, memberAge, memberPhone);
        Long saveId = memberService.save(memberDTO);
        // 로그인 객체 생성 후 로그인
        MemberDTO loginMemberDTO = new MemberDTO();
        loginMemberDTO.setMemberEmail(memberEmail);
        loginMemberDTO.setMemberPassword(memberPassword);
        MemberDTO loginResult = memberService.login(loginMemberDTO);
        // 로그인 결과가 not null 이면 테스트 통과
        assertThat(loginResult).isNotNull();
    }
}
