package hcy.gym.dto.page;

import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Data
public class PageResponseDTO<EN, DTO> {

    private List<DTO> dataList;

    private Integer page;
    private Integer size;

    private boolean prev;
    private boolean next;

    private Integer totalPage;

    private Integer start;
    private Integer end;

    private List<Integer> pageList = new ArrayList<>();

    public PageResponseDTO(Function<EN, DTO> fn, Page<EN> page) {

        dataList = page.stream().map(fn).collect(Collectors.toList());
        totalPage = page.getTotalPages();

        Pageable pageable = page.getPageable();
        makePage(pageable);
    }

    private void makePage(Pageable pageable) {

        page = pageable.getPageNumber() + 1;
        size = pageable.getPageSize();

        int tempEnd = (int) Math.ceil(page / 10.0) * 10;

        start = tempEnd - 9;

        prev = start > 1;

        end = tempEnd > totalPage ? totalPage : tempEnd;

        next = totalPage > end;

        pageList = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());

    }
}
