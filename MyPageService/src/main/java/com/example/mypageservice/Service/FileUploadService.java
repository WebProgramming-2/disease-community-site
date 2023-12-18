package com.example.mypageservice.Service;

import com.example.mypageservice.Dto.ProfileRequest;
import com.example.mypageservice.Entity.MemberProfile;
import com.example.mypageservice.Repository.MemberRepository;
import com.example.mypageservice.S3.S3Service;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class FileUploadService {

    private final S3Service s3Service;
    private final MemberRepository memberRepository;

    public void save(ProfileRequest profileRequest){
        MemberProfile member = profileRequest.toEntity();
        memberRepository.save(member);
    }

}
