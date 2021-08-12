package hcy.gym.repository.post;

import hcy.gym.domain.Category;
import lombok.Data;

@Data
public class PostSearch {

    private Category category;
    private String title;

}
