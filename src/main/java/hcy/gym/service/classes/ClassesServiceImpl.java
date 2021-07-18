package hcy.gym.service.classes;

import hcy.gym.domain.Classes;
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
        List<Classes> result = classesRepository.findByStartTime(startTime);
        return result.stream().map(this::entityToDTO).collect(Collectors.toList());
    }
}
