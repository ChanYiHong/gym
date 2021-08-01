package hcy.gym.service.comment;

import hcy.gym.domain.Category;
import hcy.gym.domain.Comment;
import hcy.gym.domain.Member;
import hcy.gym.domain.Post;
import hcy.gym.dto.comment.CommentRequestDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CommentServiceTest {

    @Autowired
    CommentService commentService;

    @Autowired
    EntityManager em;

    @Test
    @Transactional
    @Rollback
    void save() {

        Member member = Member.builder().loginId("test").name("test2").password("123123").phoneNumber("010101010101")
                .build();

        em.persist(member);

        Post post = Post.builder().title("testTitle").content("testContent").category(Category.FREE)
                .member(member).build();

        em.persist(post);

        em.flush();
        em.clear();

        Comment comment1 = Comment.builder().content("content1").member(member).post(post).deep(0).reCommentNum(1).motherId(null).subOrder(null).build();

        em.persist(comment1);
        em.flush();
        em.clear();

        // 댓글 추가 요청.
        CommentRequestDTO recomment_test = CommentRequestDTO.builder().motherId(1L).deep(1).content("recomment test").build();

        Long result = commentService.save(recomment_test, member.getId(), post.getId());

        System.out.println(result);

    }

    @Test
    @Transactional
    @Rollback
    void getList() {

        Member member = Member.builder().loginId("test").name("test2").password("123123").phoneNumber("010101010101")
                .build();

        em.persist(member);

        Post post = Post.builder().title("testTitle").content("testContent").category(Category.FREE)
                .member(member).build();

        em.persist(post);

        em.flush();
        em.clear();

        Comment comment1 = Comment.builder().content("content1").member(member).post(post).deep(0).reCommentNum(1).motherId(null).subOrder(null).build();

        em.persist(comment1);
        em.flush();
        em.clear();

        // 댓글 추가 요청.
        CommentRequestDTO recomment_test = CommentRequestDTO.builder().motherId(1L).deep(1).content("recomment test").build();

        Long result = commentService.save(recomment_test, member.getId(), post.getId());

    }
}