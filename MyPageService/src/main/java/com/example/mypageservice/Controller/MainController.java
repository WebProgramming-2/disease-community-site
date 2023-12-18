package com.example.mypageservice.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/mypage-service")
@RequiredArgsConstructor
public class MainController {//마이페이지 출력
    @GetMapping("/uploadPage")
    public String uploadPage(){
        return "/upload";
    }

    @GetMapping("/mypage")
    public String page() {
        return "index";
    }

}
