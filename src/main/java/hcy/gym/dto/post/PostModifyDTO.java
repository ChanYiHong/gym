package hcy.gym.dto.post;


import hcy.gym.domain.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostModifyDTO {

    private Long id;
    private String title;
    private String content;
    private Category category;

}
