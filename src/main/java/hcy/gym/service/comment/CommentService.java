package hcy.gym.service.comment;

import hcy.gym.domain.Comment;
import hcy.gym.domain.Member;
import hcy.gym.domain.Post;
import hcy.gym.dto.comment.CommentModifyDTO;
import hcy.gym.dto.comment.CommentRequestDTO;
import hcy.gym.dto.comment.CommentResponseDTO;
import hcy.gym.dto.page.PageRequestDTO;
import hcy.gym.dto.page.PageResponseDTO;

public interface CommentService {

    // 댓글 등록
    Long save(CommentRequestDTO commentRequestDTO, Long memberId, Long postId);

    // 댓글 목록
    PageResponseDTO<Object[], CommentResponseDTO> getList(Long postId, PageRequestDTO pageRequestDTO);

//    // 댓글 1개 (팝업용)
//    CommentResponseDTO getOne(Long commentId);

    // 댓글 수정
    void modify(CommentModifyDTO commentModifyDTO);

    // 댓글 삭제
    void remove(Long commentId);

    // 모 댓글용.
    default Comment dtoToEntityForMotherComment(CommentRequestDTO commentRequestDTO, Member member, Post post) {
        return Comment.builder()
                .content(commentRequestDTO.getContent())
                .member(member)
                .post(post)
                .deep(commentRequestDTO.getDeep())
                .reCommentNum(0)
                .motherId(null)
                .subOrder(null)
                .build();
    }

    // 대댓글용.
    default Comment dtoToEntityForReComment(CommentRequestDTO commentRequestDTO, Member member, Post post, Integer reCommentNum) {
        return Comment.builder()
                .content(commentRequestDTO.getContent())
                .member(member)
                .post(post)
                .deep(commentRequestDTO.getDeep())
                .reCommentNum(null)
                .motherId(commentRequestDTO.getMotherId())
                .subOrder(reCommentNum)
                .build();
    }

    default CommentResponseDTO entityToDTO(Comment comment, Member member) {
        return CommentResponseDTO.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .memberName(member.getName())
                .deep(comment.getDeep())
                .build();
    }

    default CommentResponseDTO entityToDTO(Comment comment) {
        return CommentResponseDTO.builder()
                .content(comment.getContent())
                .build();
    }

}
