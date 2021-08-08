package hcy.gym.dto.comment;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentResponseDTO {

    private Long id;

    private String content;

    private String memberName;

    private Integer deep;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime modifiedDate;

    private Long memberId; // 댓글 작성자와 로그인 한 사람의 id (db 고유값) 가 같으면 삭제 버튼 활성화.

}
