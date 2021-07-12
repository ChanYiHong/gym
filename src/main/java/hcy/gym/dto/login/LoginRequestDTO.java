package hcy.gym.dto.login;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LoginRequestDTO {

    @NotEmpty
    private String loginId;

    @NotEmpty
    private String password;

}
