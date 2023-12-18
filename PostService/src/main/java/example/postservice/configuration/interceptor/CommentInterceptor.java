package example.postservice.configuration.interceptor;

import example.postservice.configuration.security.UserDetailsImpl;
import example.postservice.domain.Comment;
import example.postservice.domain.Member;
import example.postservice.service.CommentService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;

@RequiredArgsConstructor
public class CommentInterceptor implements HandlerInterceptor {

    private final CommentService commentService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetailsImpl userDetails = (UserDetailsImpl) principal;
        Member loginMember = userDetails.getMember();

        int pos = requestURI.lastIndexOf("/");
        Long comment_id = Long.parseLong(requestURI.substring(pos + 1));

        Comment comment = commentService.findCommentForInterceptor(comment_id);
        if (!comment.getMember().getId().equals(loginMember.getId())) {
            response.sendRedirect("/post");
            return false;
        }
        return true;
    }
}
