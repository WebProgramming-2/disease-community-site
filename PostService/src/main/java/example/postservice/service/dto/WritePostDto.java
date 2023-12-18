package example.postservice.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class WritePostDto {

    private Long id;
    private String title;
    private String body;

    private Long categoryId;
}
