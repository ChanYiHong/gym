package hcy.gym.controller.api;

import hcy.gym.argumentresolver.Login;
import hcy.gym.dto.comment.CommentModifyDTO;
import hcy.gym.dto.comment.CommentRequestDTO;
import hcy.gym.dto.comment.CommentResponseDTO;
import hcy.gym.dto.member.MemberResponseDTO;
import hcy.gym.dto.page.PageRequestDTO;
import hcy.gym.dto.page.PageResponseDTO;
import hcy.gym.service.comment.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
@Slf4j
public class CommentApiController {

    private final CommentService commentService;

    // 댓글 저장 요청
    @PostMapping("/{postId}")
    public ResponseEntity<Long> saveComment(@PathVariable("postId") Long postId, @RequestBody CommentRequestDTO commentRequestDTO,
                                            @Login MemberResponseDTO loginMember) {
        log.info("Comment save request : {}", commentRequestDTO);

        Long result = commentService.save(commentRequestDTO, loginMember.getId(), postId);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // 댓글 목록
    @GetMapping("/list/{postId}")
    public ResponseEntity<PageResponseDTO<Object[], CommentResponseDTO>> getCommentList(@PathVariable("postId") Long postId,
                                                                                        @ModelAttribute PageRequestDTO pageRequestDTO) {

        log.info("Comment List request : {}", postId);

        PageResponseDTO<Object[], CommentResponseDTO> result = commentService.getList(postId, pageRequestDTO);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // 댓글 한 개 가져오기
    @GetMapping("/{commentId}")
    public ResponseEntity<CommentResponseDTO> getComment(@PathVariable("commentId") Long commentId) {

        log.info("Get Comment : {}", commentId);

        CommentResponseDTO result = commentService.getOne(commentId);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // 댓글 수정
    @PatchMapping("/{commentId}")
    public ResponseEntity<String> getOneComment(@RequestBody CommentModifyDTO commentModifyDTO) {

        log.info("Modify Comment : {}", commentModifyDTO);

        commentService.modify(commentModifyDTO);

        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    // 댓글 삭제
    @DeleteMapping("/{commentId}")
    public ResponseEntity<String> removeComment(@PathVariable("commentId") Long commentId) {

        log.info("Remove Comment : {}", commentId);

        commentService.remove(commentId);

        return new ResponseEntity<>("success", HttpStatus.OK);

    }
}
