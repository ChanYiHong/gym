package hcy.gym.repository.post;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import hcy.gym.domain.Category;
import hcy.gym.domain.Post;
import hcy.gym.domain.QPost;
import hcy.gym.dto.page.PageRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.StringUtils;

import java.util.List;

import static hcy.gym.domain.QPost.post;

@RequiredArgsConstructor
public class PostRepositoryCustomImpl implements PostRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Post> searchPost(PostSearch postSearch, Pageable pageable) {

        QueryResults<Post> fetchResults = queryFactory
                .select(post)
                .from(post)
                .where(
                        titleEq(postSearch.getTitle()),
                        categoryEq(postSearch.getCategory())
                )
                .orderBy(post.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        List<Post> content = fetchResults.getResults();
        long total = fetchResults.getTotal();

        return new PageImpl<>(content, pageable, total);

    }

    private BooleanExpression titleEq(String title) {
        return StringUtils.hasText(title) ? post.title.containsIgnoreCase(title) : null;
    }

    private BooleanExpression categoryEq(Category category) {
        return category != null ? post.category.eq(category) : null;
    }
}
