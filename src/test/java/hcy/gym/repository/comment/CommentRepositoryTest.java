package hcy.gym.repository.comment;

import hcy.gym.domain.Category;
import hcy.gym.domain.Comment;
import hcy.gym.domain.Member;
import hcy.gym.domain.Post;
import org.hibernate.cache.spi.entry.StructuredCacheEntry;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CommentRepositoryTest {

    @Autowired
    EntityManager em;

    @Autowired
    CommentRepository commentRepository;

    @Test
    @Transactional
    @Rollback
    void findByPostIdTest(){

        Member member = Member.builder().loginId("test").name("test2").password("123123").phoneNumber("010101010101")
                .build();

        em.persist(member);

        Post post = Post.builder().title("testTitle").content("testContent").category(Category.FREE)
                .member(member).build();

        em.persist(post);

        em.flush();
        em.clear();

//        Comment comment1 = Comment.builder().content("content1").member(member).post(post).build();
//        Comment comment2 = Comment.builder().content("content2").member(member).post(post).build();

        Comment comment1 = Comment.builder().content("content1").member(member).post(post).deep(0).build();
        Comment comment2 = Comment.builder().content("content2").member(member).post(post).deep(1).build();
        Comment comment3 = Comment.builder().content("content3").member(member).post(post).deep(0).build();

        em.persist(comment1);
        em.persist(comment2);
        em.persist(comment3);

        em.flush();
        em.clear();

        Page<Object[]> result = commentRepository.findByPostId(post.getId(), PageRequest.of(0, 10));

        List<Object[]> content = result.getContent();
        for (Object[] objects : content) {
            System.out.println((Comment) objects[0]);
            System.out.println((Member) objects[1]);
        }

    }

    @Test
    @Transactional
    @Rollback
    void findMotherCommentByPostIdTest() {
        Member member = Member.builder().loginId("test").name("test2").password("123123").phoneNumber("010101010101")
                .build();

        em.persist(member);

        Post post = Post.builder().title("testTitle").content("testContent").category(Category.FREE)
                .member(member).build();

        em.persist(post);

        em.flush();
        em.clear();

        Comment comment1 = Comment.builder().content("content1").member(member).post(post).deep(0).build();
        Comment comment2 = Comment.builder().content("content2").member(member).post(post).deep(1).build();
        Comment comment3 = Comment.builder().content("content3").member(member).post(post).deep(0).build();

        em.persist(comment1);
        em.persist(comment2);
        em.persist(comment3);

        em.flush();
        em.clear();

        Page<Object[]> result = commentRepository.findMotherCommentByPostId(post.getId(), PageRequest.of(0, 10, Sort.by("id")));

        List<Object[]> content = result.getContent();
        for (Object[] objects : content) {
            System.out.println((Comment) objects[0]);
        }
    }

}