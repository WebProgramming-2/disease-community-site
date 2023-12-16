package com.example.mypageservice.Controller;

import com.amazonaws.services.s3.AmazonS3Client;
import com.example.mypageservice.Dto.JoinRequest;
import com.example.mypageservice.Dto.ProfileRequest;
import com.example.mypageservice.Service.FileUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mypage-service")
@RequiredArgsConstructor
public class FileUploadController {

    private final AmazonS3Client amazonS3Client;
    private final FileUploadService fileUploadService;

    @PostMapping("/upload")
    public String post(@ModelAttribute ProfileRequest profileRequest){
        return fileUploadService.save(profileRequest);
    }

    @PostMapping("/sign-up")
    public void signUp(@RequestBody JoinRequest joinRequest){
        fileUploadService.signUp(joinRequest);
    }

    @PostMapping("/create")
    public void update(@RequestBody JoinRequest joinRequest){
        fileUploadService.createInfo(joinRequest);
    }
}
