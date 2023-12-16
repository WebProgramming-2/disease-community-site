package com.example.mypageservice.Dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ProfileRequest {
    private String nickname;

    private List<MultipartFile> profileImage;

    private String introduction;
}
