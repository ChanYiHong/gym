package hcy.gym.dto.classes;

import hcy.gym.domain.Week;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClassesResponseDTO {

    private Long id;
    private String name;

    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime startTime;

    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime endTime;

    private String teacher;

    // 요일
    private String day;

}
