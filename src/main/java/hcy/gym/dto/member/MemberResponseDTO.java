package hcy.gym.dto.member;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberResponseDTO {

    private Long id;
    private String loginId;
    private String name;
    private String password;
    private String phoneNumber;

}
