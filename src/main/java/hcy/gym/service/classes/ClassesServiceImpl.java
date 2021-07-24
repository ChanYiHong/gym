package hcy.gym.service.classes;

import hcy.gym.domain.Classes;
import hcy.gym.domain.Teacher;
import hcy.gym.dto.classes.ClassesResponseDTO;
import hcy.gym.repository.classes.ClassesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClassesServiceImpl implements ClassesService{

    private final ClassesRepository classesRepository;

    @Override
    public List<ClassesResponseDTO> getByStartTime(LocalTime startTime) {
        List<Object[]> result = classesRepository.findByStartTime(startTime);
        return result.stream().map(objects -> entityToDTO((Classes) objects[0], (Teacher) objects[1])).collect(Collectors.toList());
    }
}
