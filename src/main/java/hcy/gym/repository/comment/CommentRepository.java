package hcy.gym.repository.comment;

import hcy.gym.domain.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query("select c, m from Comment c left join c.post p left join c.member m where p.id = :postId")
    Page<Object[]> findByPostId(@Param("postId") Long postId, Pageable pageable);

    // 모 댓글 찾기.
    @Query("select c, m from Comment c left join c.member m left join c.post p where p.id = :postId " +
            "and c.deep = 0")
    Page<Object[]> findMotherCommentByPostId(@Param("postId") Long postId, Pageable pageable);

    // 대 댓글 찾기.
    @Query("select c, m from Comment c left join c.member m where c.motherId = :motherId")
    List<Object[]> findReCommentByMotherCommentId(@Param("motherId") Long motherId);

}
