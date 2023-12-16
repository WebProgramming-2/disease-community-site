package com.example.mypageservice.Service;

import com.example.mypageservice.Dto.JoinRequest;
import com.example.mypageservice.Dto.ProfileRequest;
import com.example.mypageservice.Entity.Member;
import com.example.mypageservice.Repository.MemberRepository;
import com.example.mypageservice.S3.S3Service;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class FileUploadService {

    private final S3Service s3Service;
    private final MemberRepository memberRepository;

    public String save(ProfileRequest profileRequest){
        Member member = memberRepository.findById(1L).orElseThrow();
        deletePrevImage(member.getProfileImage());
        String imageURL = getUploadImageFileName(profileRequest);
        member.update(profileRequest.getNickname(), imageURL);
        return imageURL;
    }


    private void deletePrevImage(String profileImage) {
        if (profileImage != null) {
            s3Service.delete(profileImage);
        }
    }

    private String getUploadImageFileName(ProfileRequest profileRequest) {

        String imageUrl = "";

        if(profileRequest.getProfileImage()==null){
            return null;
        }

        for (MultipartFile multipartFile : profileRequest.getProfileImage()) {

            if (multipartFile == null) {
                break;
            }
            // 파일명 지정 (겹치면 안되고, 확장자 빼먹지 않도록 조심!)
            String fileName = UUID.randomUUID() + multipartFile.getOriginalFilename();
            // 파일데이터와 파일명 넘겨서 S3에 저장
            try {
                imageUrl = s3Service.upload(multipartFile, fileName);
            } catch (IOException e) {
                throw new IllegalArgumentException("이미지 업로드 실패");
            }
        }
        return imageUrl;
    }

    public void signUp(JoinRequest joinRequest) {
        Member member = joinRequest.toEntity();
        memberRepository.save(member);
    }

}
