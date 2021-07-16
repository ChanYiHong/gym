package hcy.gym.service.payment;

import hcy.gym.domain.Member;
import hcy.gym.domain.MemberShip;
import hcy.gym.domain.Payment;
import hcy.gym.dto.member.MemberResponseDTO;
import hcy.gym.dto.membership.MemberShipRegisterDTO;

public interface PaymentService {

    Long register(MemberResponseDTO loginMember, MemberShipRegisterDTO registerDTO, Long memberShipId);

    default Payment dtoToEntity(MemberShipRegisterDTO registerDTO, Member member, MemberShip memberShip) {
        return Payment.builder()
                .paymentPlan(registerDTO.getPaymentPlan())
                .paymentWay(registerDTO.getPaymentWay())
                .price(memberShip.getPrice())
                .member(member)
                .memberShip(memberShip)
                .build();
    }

}
