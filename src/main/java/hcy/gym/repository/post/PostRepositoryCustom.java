package hcy.gym.repository.post;

import hcy.gym.domain.Post;
import hcy.gym.dto.page.PageRequestDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostRepositoryCustom {

    Page<Post> searchPost(PostSearch postSearch, Pageable pageable);

}
