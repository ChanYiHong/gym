package hcy.gym.repository.comment;

import hcy.gym.domain.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query("select c, m from Comment c left join c.member m left join c.post p where p.id = :postId")
    Page<Object[]> findByPostId(@Param("postId") Long postId, Pageable pageable);

}
