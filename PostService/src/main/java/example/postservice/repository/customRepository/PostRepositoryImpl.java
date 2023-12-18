package example.postservice.repository.customRepository;

import example.postservice.domain.Post;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;

public class PostRepositoryImpl implements PostRepositoryCustom {

    private final EntityManager entityManager;

    public PostRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Page<Post> findAllPageAndSearch(Pageable pageable, PostSearch postSearch) {
        // Build JPQL query
        String jpql = "SELECT p FROM Post p JOIN p.member m WHERE 1=1";
        if (postSearch != null && !postSearch.isBlank()) {
            switch (postSearch.getSearchType()) {
                case member:
                    jpql += " AND m.name = :searchWord";
                    break;
                case title:
                    jpql += " AND p.title LIKE :searchWord";
                    break;
                case body:
                    jpql += " AND p.body LIKE :searchWord";
                    break;
            }
        }

        // Create a TypedQuery
        TypedQuery<Post> query = entityManager.createQuery(jpql, Post.class);

        // Set parameters
        if (postSearch != null && !postSearch.isBlank()) {
            query.setParameter("searchWord", postSearch.getSearchWord());
        }

        // Apply pagination
        query.setFirstResult((int) pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());

        // Execute query to get the result list
        List<Post> result = query.getResultList();

        // Build count query
        String countJpql = "SELECT COUNT(p) FROM Post p JOIN p.member m WHERE 1=1";
        if (postSearch != null && !postSearch.isBlank()) {
            switch (postSearch.getSearchType()) {
                case member:
                    countJpql += " AND m.name = :searchWord";
                    break;
                case title:
                    countJpql += " AND p.title LIKE :searchWord";
                    break;
                case body:
                    countJpql += " AND p.body LIKE :searchWord";
                    break;
            }
        }

        // Create a TypedQuery for count
        TypedQuery<Long> countQuery = entityManager.createQuery(countJpql, Long.class);

        // Set parameters for count query
        if (postSearch != null && !postSearch.isBlank()) {
            countQuery.setParameter("searchWord", postSearch.getSearchWord());
        }

        // Execute count query to get the total count
        Long totalCount = countQuery.getSingleResult();

        // Return Page object using PageableExecutionUtils
        return PageableExecutionUtils.getPage(result, pageable, () -> totalCount);
    }
}
