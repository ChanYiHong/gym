package hcy.gym.service.classes;

import hcy.gym.domain.Classes;
import hcy.gym.dto.classes.ClassesResponseDTO;

import java.time.LocalTime;
import java.util.List;

public interface ClassesService {

    List<ClassesResponseDTO> getByStartTime(LocalTime startTime);

    default ClassesResponseDTO entityToDTO(Classes classes) {
        return ClassesResponseDTO.builder()
                .id(classes.getId())
                .name(classes.getName())
                .startTime(classes.getStartTime())
                .endTime(classes.getEndTime())
                .build();
    }

}
