package com.example.mypageservice.Service;

import com.example.mypageservice.Dto.JoinRequest;
import com.example.mypageservice.Dto.ProfileRequest;
import com.example.mypageservice.Entity.Member;
import com.example.mypageservice.Repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class CreateInfoService {
    private final MemberRepository memberRepository;

    public String save(ProfileRequest profileRequest){
        Member member = memberRepository.findById(1L).orElseThrow();
        deletePrevImage(member.getProfileImage());
        String imageURL = getUploadImageFileName(profileRequest);
        member.update(profileRequest.getNickname(), imageURL);
        return imageURL;
    }

    public void signUp(JoinRequest joinRequest) {
        Member member = joinRequest.toEntity();
        memberRepository.save(member);
    }
}
