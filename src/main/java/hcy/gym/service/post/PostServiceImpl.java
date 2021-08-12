package hcy.gym.service.post;

import hcy.gym.domain.Member;
import hcy.gym.domain.Post;
import hcy.gym.dto.page.PageRequestDTO;
import hcy.gym.dto.page.PageResponseDTO;
import hcy.gym.dto.post.PostModifyDTO;
import hcy.gym.dto.post.PostRequestDTO;
import hcy.gym.dto.post.PostResponseDTO;
import hcy.gym.repository.member.MemberRepository;
import hcy.gym.repository.post.PostRepository;
import hcy.gym.repository.post.PostSearch;
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
@RequiredArgsConstructor
@Slf4j
public class PostServiceImpl implements PostService{

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public Long save(PostRequestDTO postRequestDTO) {
        log.info("PostService save : {}", postRequestDTO);

        Member member = memberRepository.findById(postRequestDTO.getMemberId()).orElseThrow();

        Post post = dtoToEntity(postRequestDTO, member);

        postRepository.save(post);

        return post.getId();
    }

    @Override
    public PostResponseDTO getOne(Long postId) {

        log.info("PostService getOne : {}", postId);

        Optional<Post> optionalPost = postRepository.findById(postId);

        if (optionalPost.isEmpty()) {
            throw new IllegalArgumentException(postId + "에 해당하는 게시물이 없습니다.");
        }

        Post post = optionalPost.get();

        return entityToDTO(post);
    }

    @Override
    public PageResponseDTO<Post, PostResponseDTO> getList(PostSearch postSearch, PageRequestDTO pageRequestDTO) {

        Pageable pageable = pageRequestDTO.getPageable(Sort.by("id").descending());

        Page<Post> result = postRepository.searchPost(postSearch, pageable);

        Function<Post, PostResponseDTO> fn = this::entityToDTO;

        return new PageResponseDTO<>(fn, result);
    }

    @Override
    @Transactional
    public void modify(PostModifyDTO postModifyDTO) {

        log.info("PostService modify : {}", postModifyDTO);
        Optional<Post> optionalPost = postRepository.findById(postModifyDTO.getId());

        if (optionalPost.isEmpty()) {
            throw new IllegalArgumentException(postModifyDTO.getId() + "에 해당하는 게시물이 없습니다.");
        }

        Post post = optionalPost.get();

        // 수정 (더티 체킹)
        post.changeTitle(postModifyDTO.getTitle());
        post.changeContent(postModifyDTO.getContent());
        post.changeCategory(postModifyDTO.getCategory());
    }

    @Override
    @Transactional
    public void remove(Long postId) {
        log.info("PostService remove : {}", postId);

        postRepository.deleteById(postId);
    }
}
