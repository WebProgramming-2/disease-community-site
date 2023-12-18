package com.example.mypageservice.Controller;

import com.example.mypageservice.Dto.ProfileRequest;
import com.example.mypageservice.Service.FileUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/mypage-service")
@RequiredArgsConstructor
public class FileUploadController {

    private final FileUploadService fileUploadService;

    @PostMapping("/upload-myPage")
    public String post(@ModelAttribute ProfileRequest profileRequest, Model model) {
        fileUploadService.save(profileRequest);
            model.addAttribute("nickname", profileRequest.getNickname());
            model.addAttribute("introduction", profileRequest.getIntroduction());

        return "/index";
    }


}
