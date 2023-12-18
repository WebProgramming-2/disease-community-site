package example.postservice.controller;

import example.postservice.configuration.security.UserDetailsImpl;
import example.postservice.service.CommentService;
import example.postservice.service.dto.CommentDto;
import example.postservice.service.dto.EditCommentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/post/{post_id}/comment/edit/{comment_id}")
    public String editForm(@PathVariable Long post_id, @PathVariable Long comment_id, Model model) {
        EditCommentDto commentDto = commentService.findCommentDto(comment_id);
        model.addAttribute("commentDto", commentDto);

        return "comment/editform";
    }

    @PostMapping("/post/{post_id}/comment")
    public String addComment(@PathVariable Long post_id, RedirectAttributes redirectAttributes,
                             @ModelAttribute CommentDto commentDto,
                             @AuthenticationPrincipal UserDetailsImpl userDetails) {

        commentService.save(commentDto, post_id, userDetails.getMember().getId());
        redirectAttributes.addAttribute("post_id", post_id);

        return "redirect:/post/{post_id}";
    }

    @PatchMapping("/post/{post_id}/comment/{comment_id}")
    public String updateComment(@PathVariable Long post_id, @PathVariable Long comment_id,
                                @ModelAttribute CommentDto commentDto, RedirectAttributes redirectAttributes) {

        commentService.update(comment_id, commentDto);
        redirectAttributes.addAttribute("post_id", post_id);

        return "redirect:/post/{post_id}";
    }

    @DeleteMapping("/post/{post_id}/comment/{comment_id}")
    public String deleteComment(@PathVariable Long post_id, @PathVariable Long comment_id,
                                RedirectAttributes redirectAttributes) {
        commentService.delete(comment_id, post_id);
        redirectAttributes.addAttribute("post_id", post_id);

        return "redirect:/post/{post_id}";
    }
}
