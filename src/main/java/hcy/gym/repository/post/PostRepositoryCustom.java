package hcy.gym.repository.post;

import hcy.gym.domain.Post;
import hcy.gym.dto.page.PageRequestDTO;
import org.springframework.data.domain.Page;

public interface PostRepositoryCustom {

    Page<Post> searchPost(PostSearch postSearch, PageRequestDTO pageRequestDTO);

}
