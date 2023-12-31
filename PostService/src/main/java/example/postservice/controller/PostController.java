package example.postservice.controller;

import example.postservice.configuration.security.UserDetailsImpl;
import example.postservice.domain.Category;
import example.postservice.repository.customRepository.PostSearch;
import example.postservice.repository.customRepository.SearchType;
import example.postservice.service.CategoryService;
import example.postservice.service.HeartService;
import example.postservice.service.PostService;
import example.postservice.service.dto.CommentDto;
import example.postservice.service.dto.PostDto;
import example.postservice.service.dto.PostListDto;
import example.postservice.service.dto.WritePostDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final HeartService heartService;
    private final CategoryService categoryService;

    @GetMapping("/post")
    public String postList(Model model, @ModelAttribute("postSearch") PostSearch postSearch,
                           @PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<PostListDto> postList = postService.findList(pageable, postSearch);
        List<Category> categories = categoryService.findList();

        model.addAttribute("postListDto", postList.getContent());
        model.addAttribute("categories", categories);

        int startPage = getStartPage(postList);

        model.addAttribute("startPage", getStartPage(postList));
        model.addAttribute("currentPage", postList.getNumber() + 1);
        model.addAttribute("endPage", getEndPage(postList, startPage));
        model.addAttribute("hasPrevious", postList.hasPrevious());
        model.addAttribute("hasNext", postList.hasNext());
        model.addAttribute("searchTypes", SearchType.values());

        return "post/list";
    }

    @GetMapping("/post/{post_id}")
    public String post(@PathVariable Long post_id, Model model,
                       @ModelAttribute("commentDto") CommentDto commentDto,
                       @AuthenticationPrincipal UserDetailsImpl userDetails) {
        PostDto postDto = postService.findPostAndComment(post_id);
        model.addAttribute("postDto", postDto);
        model.addAttribute("guest_id", userDetails.getMember().getId());

        return "post/post";
    }

    @GetMapping("/post/add")
    public String addPostForm(@ModelAttribute("postDto") WritePostDto writePostDto, Model model) {
        List<Category> categories = categoryService.findList();
        model.addAttribute("categories", categories);
        return "post/addform";
    }

    @GetMapping("/post/edit/{post_id}")
    public String editPostForm(@PathVariable Long post_id, Model model) {
        WritePostDto writePostDto = postService.findWritePostDto(post_id);
        model.addAttribute("postDto", writePostDto);

        return "post/editform";
    }

    @PostMapping("/post")
    public String createPost(@ModelAttribute WritePostDto writePostDto,
                             @RequestParam("categoryId") Long categoryId,
                             RedirectAttributes redirectAttributes,
                             @AuthenticationPrincipal UserDetailsImpl userDetails) {

        Long post_id = postService.createPost(writePostDto, userDetails.getMember().getId(), categoryId);
        redirectAttributes.addAttribute("post_id", post_id);

        return "redirect:/post/{post_id}";
    }

    @PatchMapping("/post/{post_id}")
    public String updatePost(@PathVariable Long post_id,
                             @ModelAttribute WritePostDto writePostDto,
                             RedirectAttributes redirectAttributes) {

        if (post_id != null) postService.updatePost(post_id, writePostDto);

        redirectAttributes.addAttribute("post_id", post_id);

        return "redirect:/post/{post_id}";
    }

    @DeleteMapping("/post/{post_id}")
    public String deletePost(@PathVariable Long post_id) {
        if (post_id != null) postService.delete(post_id);

        return "redirect:/post";
    }

    @PostMapping("/post/{post_id}/heart")
    public String changeHeartStatus(@PathVariable Long post_id,
                                    @AuthenticationPrincipal UserDetailsImpl userDetails,
                                    RedirectAttributes redirectAttributes) {

        heartService.changeHeartStatus(post_id, userDetails.getMember().getId());

        redirectAttributes.addAttribute("post_id", post_id);
        return "redirect:/post/{post_id}";
    }

    private static int getStartPage(Page<PostListDto> postList) {
        return ((postList.getNumber() / 5) * 5) + 1;
    }

    private static int getEndPage(Page<PostListDto> postList, int startPage) {
        if (postList.getTotalPages() == 0) return 1;
        return Math.min(postList.getTotalPages(), startPage + 4);
    }
}
