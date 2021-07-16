package hcy.gym.domain;

import hcy.gym.converter.PaymentPlanConverter;
import hcy.gym.converter.PaymentWayConverter;
import lombok.*;

import javax.persistence.*;

/**
 * 회원의 결제 방법 및 정보 저장.
 * 카드, 현금
 * 일시불, 할부 (2~12개월)
 * 금액
 */
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = {"memberShip"})
public class Payment extends BaseEntity{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Convert(converter = PaymentPlanConverter.class)
    private PaymentPlan paymentPlan;

    @Convert(converter = PaymentWayConverter.class)
    private PaymentWay paymentWay;

    private Integer price;

    // 어떤 멤버쉽인지.. 다 대 1 관계.
    @ManyToOne(fetch = FetchType.LAZY)
    private MemberShip memberShip;

    // 1:1 관계 멤버와
    @OneToOne(fetch = FetchType.LAZY)
    private Member member;

}
