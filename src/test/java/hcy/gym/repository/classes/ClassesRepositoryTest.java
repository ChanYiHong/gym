package hcy.gym.repository.classes;

import hcy.gym.domain.Classes;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ClassesRepositoryTest {

    @Autowired
    ClassesRepository classesRepository;

    @Test
    void findByStartTimeTest() {
        List<Classes> result = classesRepository.findByStartTime(LocalTime.of(9, 30));

        for (Classes classes : result) {
            System.out.println(classes);
        }
    }

}