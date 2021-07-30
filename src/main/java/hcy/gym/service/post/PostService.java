package hcy.gym.service.post;

import hcy.gym.domain.Member;
import hcy.gym.domain.Post;
import hcy.gym.dto.page.PageRequestDTO;
import hcy.gym.dto.page.PageResponseDTO;
import hcy.gym.dto.post.PostModifyDTO;
import hcy.gym.dto.post.PostRequestDTO;
import hcy.gym.dto.post.PostResponseDTO;

public interface PostService {

    // 게시글 등록
    Long save(PostRequestDTO postRequestDTO);

    // 게시글 단건 조회
    PostResponseDTO getOne(Long postId);

    // 게시글 전체 조회 (페이징)
    PageResponseDTO<Post, PostResponseDTO> getList(PageRequestDTO pageRequestDTO);

    // 게시글 수정
    void modify(PostModifyDTO postModifyDTO);

    // 게시글 삭제
    void remove(Long postId);

    default Post dtoToEntity(PostRequestDTO postRequestDTO, Member member) {
        return Post.builder()
                .title(postRequestDTO.getTitle())
                .content(postRequestDTO.getContent())
                .category(postRequestDTO.getCategory())
                .member(member)
                .build();
    }

    default PostResponseDTO entityToDTO(Post post) {
        return PostResponseDTO.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .category(post.getCategory())
                .modifiedDate(post.getLastModifiedDate())
                .build();
    }

}
