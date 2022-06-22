package com.its.member.service;

import com.its.member.dto.MemberDTO;
import com.its.member.entity.MemberEntity;
import com.its.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Long save(MemberDTO memberDTO) {
        MemberEntity memberEntity = MemberEntity.toEntity(memberDTO);
        Long id = memberRepository.save(memberEntity).getId();
        return id;

    }

    public MemberDTO findById(Long saveId) {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findById(saveId);
        if(optionalMemberEntity.isPresent()) {
            return MemberDTO.toDTO(optionalMemberEntity.get());
        } else {
            return null;
        }
    }
}
