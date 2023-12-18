package example.postservice.controller;

import example.postservice.configuration.security.UserDetailsImpl;
import example.postservice.service.MemberService;
import example.postservice.service.dto.LoginDto;
import example.postservice.service.dto.MemberJoinDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/join")
    public String joinForm(@ModelAttribute("memberDto") MemberJoinDto memberJoinDto) {
        return "member/join";
    }

    @GetMapping("/login")
    public String loginForm(@ModelAttribute("loginDto") LoginDto loginDto,
                            @AuthenticationPrincipal UserDetails userDetails) {
        return (userDetails == null) ? "member/login" : "redirect:/";
    }

    @PostMapping("/member")
    public String join(@ModelAttribute MemberJoinDto memberJoinDto) {
        memberService.save(memberJoinDto);
        return "redirect:/";
    }

    @DeleteMapping("/member/{member_id}")
    public String deleteMember(@PathVariable Long member_id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        if (member_id.equals(userDetails.getMember().getId())) {
            memberService.delete(member_id);
            SecurityContextHolder.clearContext();
        }

        return "redirect:/";
    }

}
