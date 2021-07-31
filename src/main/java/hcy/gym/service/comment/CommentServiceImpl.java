package hcy.gym.service.comment;

import hcy.gym.domain.Comment;
import hcy.gym.domain.Member;
import hcy.gym.domain.Post;
import hcy.gym.dto.comment.CommentModifyDTO;
import hcy.gym.dto.comment.CommentRequestDTO;
import hcy.gym.dto.comment.CommentResponseDTO;
import hcy.gym.dto.page.PageRequestDTO;
import hcy.gym.dto.page.PageResponseDTO;
import hcy.gym.repository.comment.CommentRepository;
import hcy.gym.repository.member.MemberRepository;
import hcy.gym.repository.post.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.function.Function;

@Service
@Transactional(readOnly = true)
@Slf4j
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{

    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;
    private final PostRepository postRepository;

    @Override
    @Transactional
    public Long save(CommentRequestDTO commentRequestDTO, Long memberId, Long postId) {

        Member member = memberRepository.findById(memberId).orElseThrow();
        Optional<Post> postOptional = postRepository.findById(postId);
        if (postOptional.isEmpty()) {
            throw new IllegalArgumentException("해당 post가 존재하지 않습니다. : " + postId);
        }
        Post post = postOptional.get();

        Comment comment = dtoToEntity(commentRequestDTO, member, post);

        commentRepository.save(comment);

        return comment.getId();

    }

    @Override
    public PageResponseDTO<Object[], CommentResponseDTO> getList(Long postId, PageRequestDTO pageRequestDTO) {

        Pageable pageable = pageRequestDTO.getPageable(Sort.by("id").ascending());

        Page<Object[]> result = commentRepository.findByPostId(postId, pageable);

        Function<Object[], CommentResponseDTO> fn = objects -> entityToDTO((Comment) objects[0], (Member) objects[1]);

        return new PageResponseDTO<>(fn, result);
    }

    @Override
    public CommentResponseDTO getOne(Long commentId) {

        Optional<Comment> commentOptional = commentRepository.findById(commentId);

        if (commentOptional.isEmpty()) {
            throw new IllegalArgumentException("해당 댓글이 없습니다 : " + commentId);
        }

        return entityToDTO(commentOptional.get());

    }

    @Override
    public void modify(CommentModifyDTO commentModifyDTO) {

        Optional<Comment> optionalComment = commentRepository.findById(commentModifyDTO.getId());

        if (optionalComment.isEmpty()) {
            throw new IllegalArgumentException("해당 댓글이 없음 : " + commentModifyDTO.getId());
        }

        Comment comment = optionalComment.get();

        comment.changeContent(commentModifyDTO.getContent());

    }

    @Override
    public void remove(Long commentId) {

        commentRepository.deleteById(commentId);

    }
}
