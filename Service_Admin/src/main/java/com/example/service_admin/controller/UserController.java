package com.example.service_admin.controller;

import com.example.service_admin.dto.user.request.RequestBanUser;
import com.example.service_admin.dto.user.request.RequestGivingAuthority;
import com.example.service_admin.dto.user.response.ReportedUserResponse;
import com.example.service_admin.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/administration")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/confirm")
    public boolean confirm(@RequestParam long user_id){
        return this.userService.confirm(user_id);
    }
    @PutMapping("/give")
    public void give(@RequestBody RequestGivingAuthority request) {
        this.userService.give(request);
    }

    @GetMapping("/repousers")
    public List<ReportedUserResponse> getReportedUsers(){
        return this.userService.getReportedUsers();
    }

    @DeleteMapping("/passuser")
    public void passUser(@RequestParam long id){
        this.userService.removeAtTable(id);
    }

    @PutMapping("/warnuser")
    public void warnUser(@RequestParam long id){
        int count = this.userService.getUser(id).getWarn_count();
        if(count > 3)
            this.userService.warn(id);
        else {
            RequestBanUser requestBanUser = new RequestBanUser(id,
                    LocalDateTime.now(), LocalDateTime.now().minusMonths(1));
            this.banUser(requestBanUser);
        }
    }

    @PostMapping("ban")
    public void banUser(@RequestBody RequestBanUser request){
        this.userService.ban(request);
    }

    @DeleteMapping("/banrelease")
    public void banRelease(long id){
        this.userService.banRelease(id);
    }

    @DeleteMapping("/deluser")
    public void delUser(@RequestParam long id){
        this.userService.removeAtTable(id);
        //홍씨 불러서 지워달라하기
    }

    @PutMapping("/delprofile")
    public void delProfile(@RequestParam long id){
//        홍씨에게 부탁. 아래는 안되면 사용
//        this.userService.delProfile();
    }
}
