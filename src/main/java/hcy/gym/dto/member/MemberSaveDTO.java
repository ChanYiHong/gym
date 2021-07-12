package hcy.gym.dto.member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberSaveDTO {

    private String loginId;
    private String name;
    private String password;
    private String phoneNumber;

}
