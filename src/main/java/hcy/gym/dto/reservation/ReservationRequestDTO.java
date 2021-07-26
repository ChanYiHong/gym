package hcy.gym.dto.reservation;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ReservationRequestDTO {

    private Long memberId;
    private Long classId;

    private Integer year;
    private Integer month;
    private Integer day;
    private Integer hour;
    private Integer minute;

}
