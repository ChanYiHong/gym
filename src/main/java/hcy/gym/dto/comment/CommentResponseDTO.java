package hcy.gym.dto.comment;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentResponseDTO {

    private String content;

    private String memberName;

    private Integer deep;

}
