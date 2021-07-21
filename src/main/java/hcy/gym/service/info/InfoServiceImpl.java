package hcy.gym.service.info;

import hcy.gym.domain.Info;
import hcy.gym.dto.info.InfoResponseDTO;
import hcy.gym.dto.page.PageRequestDTO;
import hcy.gym.dto.page.PageResponseDTO;
import hcy.gym.repository.info.InfoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
@Slf4j
public class InfoServiceImpl implements InfoService{

    private final InfoRepository infoRepository;

    @Override
    public PageResponseDTO<Info, InfoResponseDTO> getList(PageRequestDTO pageRequestDTO) {

        Page<Info> result = infoRepository.findAll(pageRequestDTO.getPageable(Sort.by("id").ascending()));
        Function<Info, InfoResponseDTO> fn = this::entityToDTO;
        return new PageResponseDTO<>(fn, result);

    }
}
