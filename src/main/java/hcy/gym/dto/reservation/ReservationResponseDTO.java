package hcy.gym.dto.reservation;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ReservationResponseDTO {

    // 수업 예약 id.
    private Long id;

    // 수업 시간 정보.
    private Integer year;
    private Integer month;
    private Integer day;

    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime startTime;
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime endTime;

    private String dayOfWeek;

    // 수업 이름.
    private String name;

    // 담당 선생님 이름.
    private String teacher;

}
