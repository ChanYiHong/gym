package hcy.gym.service.info;

import hcy.gym.domain.Info;
import hcy.gym.dto.info.InfoResponseDTO;
import hcy.gym.dto.page.PageRequestDTO;
import hcy.gym.dto.page.PageResponseDTO;

public interface InfoService {

    PageResponseDTO<Info, InfoResponseDTO> getList(PageRequestDTO pageRequestDTO);

    default InfoResponseDTO entityToDTO(Info info) {
         return InfoResponseDTO.builder()
                .id(info.getId())
                .name(info.getName())
                .build();
    }

}
