package example.postservice.service;

import example.postservice.domain.Category;
import example.postservice.domain.Member;
import example.postservice.domain.Post;
import example.postservice.repository.*;
import example.postservice.repository.customRepository.PostSearch;
import example.postservice.service.dto.CommentDto;
import example.postservice.service.dto.PostDto;
import example.postservice.service.dto.PostListDto;
import example.postservice.service.dto.WritePostDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private final CommentRepository commentRepository;
    private final HeartRepository heartRepository;
    private final CategoryRepository categoryRepository;

    public Page<PostListDto> findList(Pageable pageable, PostSearch postSearch) {
        Page<Post> find;
        if(postSearch.getCategoryId() != null) {
            find = postRepository.findByCategoryId(postSearch.getCategoryId(), pageable);
        } else {
            find = postRepository.findAllPageAndSearch(pageable, postSearch);
        }
        return find.map(PostListDto::new);
    }

    public PostDto findPostAndComment(Long post_id) {
        Post findPost = postRepository.findById(post_id).orElseThrow(IllegalArgumentException::new);

        List<CommentDto> commentDtos = findPost.getComments().stream().map(comment -> {
            return new CommentDto(comment.getId(), comment.getBody(), comment.getMember().getId(), comment.getMember().getName());
        }).collect(Collectors.toList());

        return new PostDto(findPost.getId(), findPost.getTitle(), findPost.getBody(), findPost.getMember().getId(), findPost.getMember().getName(), findPost.getHearts().size(), findPost.getCreatedDate(), commentDtos);
    }

    public WritePostDto findWritePostDto(Long post_id) {
        Post findPost = postRepository.findById(post_id).orElseThrow(IllegalArgumentException::new);
        return new WritePostDto(findPost.getId(), findPost.getTitle(), findPost.getBody(), findPost.getCategory().getId());
    }

    public Post findPostForInterceptor(Long postId) {
        return postRepository.findById(postId).orElseThrow(IllegalArgumentException::new);
    }

    @Transactional
    public Long createPost(WritePostDto writePostDto, Long id, Long categoryId) {
        Member findMember = memberRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        Category category = categoryRepository.findById(categoryId).orElseThrow(IllegalArgumentException::new);

        Post post = Post.createPost(writePostDto.getTitle(), writePostDto.getBody(), findMember, category);

        return postRepository.save(post).getId();
    }

    @Transactional
    public void updatePost(Long post_id, WritePostDto writePostDto) {
        Post post = postRepository.findById(post_id).orElseThrow(IllegalArgumentException::new);
        post.update(writePostDto.getTitle(), writePostDto.getBody());
    }

    @Transactional
    public void delete(Long post_id) {
        heartRepository.deleteByPostId(post_id);
        commentRepository.deleteByPostId(post_id);
        postRepository.deleteByPostId(post_id);
    }

    @Transactional
    public void deleteByMemberId(Long member_id) {
        List<Post> findPosts = postRepository.findAllByMemberId(member_id);
        List<Long> postIds = findPosts.stream().map(post -> {return post.getId();}).collect(Collectors.toList());

        heartRepository.deleteByPostIds(postIds);
        commentRepository.deleteByPostIds(postIds);
        postRepository.deleteByPostIds(postIds);
    }

    public Page<PostListDto> findByCategoryId(Pageable pageable, Long categoryId) {
        // 해당 카테고리에 속하는 게시물을 페이지네이션하여 조회
        Page<Post> posts = postRepository.findByCategoryId(categoryId, pageable);

        // Entity를 DTO로 변환
        List<PostListDto> postListDtos = posts.getContent().stream()
                .map(PostListDto::new)  // PostListDto의 생성자에 Post 객체를 넘겨주는 예시
                .collect(Collectors.toList());

        return new PageImpl<>(postListDtos, pageable, posts.getTotalElements());
    }
}
