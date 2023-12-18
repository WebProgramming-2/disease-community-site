package example.postservice.service;

import example.postservice.domain.Heart;
import example.postservice.domain.Member;
import example.postservice.domain.Post;
import example.postservice.repository.HeartRepository;
import example.postservice.repository.MemberRepository;
import example.postservice.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class HeartService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private final HeartRepository heartRepository;

    @Transactional
    public void changeHeartStatus(Long post_id, Long member_id) {

        heartRepository.findByMemberIdAndPostId(post_id, member_id).ifPresentOrElse(
                heart -> heartRepository.delete(heart),
                () -> {
                    Post post = postRepository.findById(post_id).orElseThrow(IllegalArgumentException::new);
                    Member member = memberRepository.findById(member_id).orElseThrow(IllegalArgumentException::new);

                    heartRepository.save(Heart.createHeart(post, member));
                });
    }
}