package hcy.gym.dto.membership;

import lombok.*;
import org.springframework.format.annotation.NumberFormat;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class MemberShipResponseDTO {

    private Long id;
    private String name;

    @NumberFormat(pattern = "###,###")
    private Integer price;

    private Integer month;
    private Integer week;

}
