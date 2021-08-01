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
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

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

        // 모댓글, 대댓글 판별
        // 대댓글일 경우
        if (commentRequestDTO.getDeep() == 1) {

            // 일단 모댓글을 찾는다.
            Optional<Comment> optionalComment = commentRepository.findById(commentRequestDTO.getMotherId());

            if (optionalComment.isEmpty()) {
                throw new IllegalArgumentException("해당 모 댓글이 없습니다. id : " + commentRequestDTO.getMotherId());
            }

            Comment motherComment = optionalComment.get();

            // 모댓글에서 대댓글 수를 가져온다. 대댓글의 수가 곧 대댓글의 순서가 됨.
            Integer reCommentNum = motherComment.getReCommentNum();
            motherComment.addReCommentNum();

            Comment comment = dtoToEntityForReComment(commentRequestDTO, member, post, reCommentNum);

            commentRepository.save(comment);

            return comment.getId();

        }
        // 모댓글의 경우.
        else {

            Comment comment = dtoToEntityForMotherComment(commentRequestDTO, member, post);

            commentRepository.save(comment);

            return comment.getId();

        }

    }

    @Override
    public PageResponseDTO<Object[], CommentResponseDTO> getList(Long postId, PageRequestDTO pageRequestDTO) {

        // 모댓글 id 순서로 가져오자.

        Pageable pageable = pageRequestDTO.getPageable(Sort.by("id").ascending());
        Page<Object[]> result = commentRepository.findMotherCommentByPostId(postId, pageable);

        // return 시킬 comment들.. 페이징이 문제다..
        List<Object[]> comments = new ArrayList<>();

        // 모댓글마다 하나씩 쿼리를.. 일단 해보자.

        List<Object[]> content = result.getContent();
        for (Object[] objects : content) {
            Comment motherComment = (Comment) objects[0];
            // 모 댓글이 먼저 들어감.
            comments.add(objects);
            // 대댓글을 모댓글 id를 통해 가져옴.
            List<Object[]> reComments = commentRepository.findReCommentByMotherCommentId(motherComment.getId());
            // 대댓글을 dto로 바꾸고 comments에 추가.
            comments.addAll(reComments);
        }

        // 일단 페이징이.. 되..나? ㅋㅋ (페이지 번호는 처음에 요청한 번호, 사이즈는 지금 대댓글 개수가 포함된 사이즈 원래 10이면 10 이상이 됨)
        Page<Object[]> pages = new PageImpl<>(comments, PageRequest.of(pageRequestDTO.getPage(), comments.size()), result.getTotalElements());

        Function<Object[], CommentResponseDTO> fn = objects -> entityToDTO((Comment) objects[0], (Member) objects[1]);

        return new PageResponseDTO<>(fn, pages);

    }

//    @Override
//    public CommentResponseDTO getOne(Long commentId) {
//
//        Optional<Comment> commentOptional = commentRepository.findById(commentId);
//
//        if (commentOptional.isEmpty()) {
//            throw new IllegalArgumentException("해당 댓글이 없습니다 : " + commentId);
//        }
//
//        return entityToDTO(commentOptional.get());
//
//    }

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
