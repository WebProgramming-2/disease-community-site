package example.postservice.configuration.interceptor;

import example.postservice.configuration.security.UserDetailsImpl;
import example.postservice.domain.Member;
import example.postservice.domain.Post;
import example.postservice.service.PostService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @brief member_id(pk, fk) 를 비교 하여 Post 접근 권한 인가
 */
@RequiredArgsConstructor
public class PostInterceptor implements HandlerInterceptor {

    private final PostService postService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetailsImpl userDetails = (UserDetailsImpl) principal;
        Member loginMember = userDetails.getMember();

        HandlerMethod method = (HandlerMethod) handler;

        int pos = requestURI.lastIndexOf("/");
        Long postId = Long.parseLong(requestURI.substring(pos + 1));

        // 단순 조회 로직일 경우
        if (method.getMethod().getName().equals("post")) return true;

        Post post = postService.findPostForInterceptor(postId);
        if (!post.getMember().getId().equals(loginMember.getId())) {
            response.sendRedirect("/post");
            return false;
        }
        return true;
    }
}
