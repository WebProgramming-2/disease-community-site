package example.postservice.repository.customRepository;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PostSearch {
    private SearchType searchType;
    private String searchWord;
    private Long categoryId;

    public boolean isBlank() {
        return searchType == null && (searchWord == null || categoryId == null);
    }
}
