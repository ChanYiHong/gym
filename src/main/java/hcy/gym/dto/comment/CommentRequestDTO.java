package hcy.gym.dto.comment;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentRequestDTO {

//    private Long memberId; // 댓글 작성자 id

//    private Long postId; // 게시글 id

    private String content; // 댓글 내용

    private Long motherId; // 대댓글일 경우 모댓글 id

    private Integer deep; // 모댓글 0, 대댓글 1

}
