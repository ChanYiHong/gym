package hcy.gym.dto.classes;

import lombok.*;

import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClassesResponseDTO {

    private String name;
    private LocalTime startTime;
    private LocalTime endTime;

}
