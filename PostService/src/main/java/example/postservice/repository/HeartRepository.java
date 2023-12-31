package example.postservice.repository;

import example.postservice.domain.Heart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface HeartRepository extends JpaRepository<Heart, Long> {

    @Query("select h from Heart h where h.post.id = :post_id and h.member.id = :member_id")
    Optional<Heart> findByMemberIdAndPostId(@Param("post_id") Long post_id, @Param("member_id") Long member_id);

    @Modifying
    @Query("delete from Heart h where h.post.id = :post_id")
    void deleteByPostId(@Param("post_id") Long post_id);

    @Modifying
    @Query("delete from Heart h where h.member.id = :member_id")
    void deleteByMemberId(@Param("member_id") Long member_id);

    @Modifying
    @Query("delete from Heart h where h.post.id in :post_ids")
    void deleteByPostIds(@Param("post_ids") List<Long> postIds);
}
