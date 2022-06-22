package com.its.member.test;

import com.its.member.dto.MemberDTO;
import com.its.member.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
@SpringBootTest
public class TestClass {

    @Autowired
    private MemberService memberService;

    @Test
    @Transactional
    @Rollback
    public void saveTest() {
        MemberDTO memberDTO = new MemberDTO("aa", "aa", "aa", 10, "aa");
        Long saveId = memberService.save(memberDTO);
        MemberDTO findDTO = memberService.findById(saveId);
        assertThat(memberDTO.getMemberEmail()).isEqualTo(findDTO.getMemberEmail());
    }
}
