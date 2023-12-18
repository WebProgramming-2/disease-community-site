package example.postservice.repository.customRepository;

import example.postservice.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostRepositoryCustom {

    Page<Post> findAllPageAndSearch(Pageable pageable, PostSearch postSearch);
}
