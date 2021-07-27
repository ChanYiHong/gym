package hcy.gym.service.classes;

import hcy.gym.domain.Classes;
import hcy.gym.domain.Teacher;
import hcy.gym.dto.classes.ClassesResponseDTO;
import hcy.gym.repository.classes.ClassesRepository;
import hcy.gym.repository.reservation.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClassesServiceImpl implements ClassesService{

    private final ClassesRepository classesRepository;
    private final ReservationRepository reservationRepository;

    @Override
    public List<ClassesResponseDTO> getByStartTime(LocalTime startTime) {
        List<Object[]> result = classesRepository.findByStartTime(startTime);

        // 예약 몇 명인지? 까지 계산해서 넘김.
        return result.stream().map(objects -> {
            Classes classes = (Classes) objects[0];
            Integer countOfClassMember = reservationRepository.findCountOfClassMember(classes);
            return entityToDTO(classes, (Teacher) objects[1], countOfClassMember);
        }).collect(Collectors.toList());

    }
}
