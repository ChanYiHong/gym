package hcy.gym.service.payment;

import hcy.gym.domain.Member;
import hcy.gym.domain.MemberShip;
import hcy.gym.domain.Payment;
import hcy.gym.dto.member.MemberResponseDTO;
import hcy.gym.dto.membership.MemberShipRegisterDTO;
import hcy.gym.dto.payment.PaymentInfoDTO;

public interface PaymentService {

    Long register(MemberResponseDTO loginMember, MemberShipRegisterDTO registerDTO, Long memberShipId);

    PaymentInfoDTO getByMemberId(Long memberId);

    default Payment dtoToEntity(MemberShipRegisterDTO registerDTO, Member member, MemberShip memberShip) {
        return Payment.builder()
                .paymentPlan(registerDTO.getPaymentPlan())
                .paymentWay(registerDTO.getPaymentWay())
                .price(memberShip.getPrice())
                .member(member)
                .memberShip(memberShip)
                .build();
    }

    default PaymentInfoDTO entityToDTO(Payment payment, MemberShip memberShip) {
        return PaymentInfoDTO.builder()
                .name(memberShip.getName())
                .paymentPlan(payment.getPaymentPlan())
                .paymentWay(payment.getPaymentWay())
                .price(memberShip.getPrice())
                .startTime(payment.getCreatedDate().toLocalDate())
                .build();
    }

}
