package example.postservice.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class EditCommentDto {
    private Long id;
    private String body;
}
