package hcy.gym.service.classes;

import hcy.gym.domain.Classes;
import hcy.gym.domain.Teacher;
import hcy.gym.dto.classes.ClassesResponseDTO;

import java.time.LocalTime;
import java.util.List;

public interface ClassesService {

    List<ClassesResponseDTO> getByStartTime(LocalTime startTime);

    default ClassesResponseDTO entityToDTO(Classes classes, Teacher teacher) {
        return ClassesResponseDTO.builder()
                .id(classes.getId())
                .name(classes.getName())
                .startTime(classes.getStartTime())
                .endTime(classes.getEndTime())
                .teacher(teacher.getName())
                .build();
    }

}
