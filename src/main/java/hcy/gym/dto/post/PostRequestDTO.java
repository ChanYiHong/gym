package hcy.gym.dto.post;

import hcy.gym.domain.Category;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostRequestDTO {

    // 회원 id
    private Long memberId;

    // 제목
    @NotEmpty
    private String title;

    // 내용
    @NotEmpty
    private String content;

    // 카테고리
    @NotNull
    private Category category;

}
