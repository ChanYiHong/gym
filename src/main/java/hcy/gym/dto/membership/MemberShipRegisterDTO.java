package hcy.gym.dto.membership;

import hcy.gym.domain.PaymentPlan;
import hcy.gym.domain.PaymentWay;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class MemberShipRegisterDTO {

    private PaymentPlan paymentPlan;

    private PaymentWay paymentWay;

}
