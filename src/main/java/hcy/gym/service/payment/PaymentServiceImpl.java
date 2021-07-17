package hcy.gym.service.payment;

import hcy.gym.domain.Member;
import hcy.gym.domain.MemberShip;
import hcy.gym.domain.Payment;
import hcy.gym.dto.member.MemberResponseDTO;
import hcy.gym.dto.membership.MemberShipRegisterDTO;
import hcy.gym.dto.payment.PaymentInfoDTO;
import hcy.gym.repository.member.MemberRepository;
import hcy.gym.repository.membership.MemberShipRepository;
import hcy.gym.repository.payment.PaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentServiceImpl implements PaymentService{

    private final PaymentRepository paymentRepository;
    private final MemberRepository memberRepository;
    private final MemberShipRepository memberShipRepository;

    @Override
    @Transactional
    public Long register(MemberResponseDTO loginMember, MemberShipRegisterDTO registerDTO, Long memberShipId) {

        MemberShip memberShip = memberShipRepository.findById(memberShipId).orElseThrow();

        Member member = memberRepository.findByLoginIdAndPassword(loginMember.getLoginId(), loginMember.getPassword())
                .orElseThrow();

        Payment payment = dtoToEntity(registerDTO, member, memberShip);

        paymentRepository.save(payment);

        return payment.getId();

    }

    /** 회원 정보창에 회원 멤버쉽 상태 표현 **/
    @Override
    public PaymentInfoDTO getByMemberId(Long memberId) {

        List<Object[]> result = paymentRepository.findByMemberId(memberId);

        // 만약 가입한 payment (결제) 가 없을경우!
        if (result.size() == 0) {
            log.info("가입한 멤버쉽이 없습니다...");
            return null;
        }

        Payment payment = (Payment) result.get(0)[0];
        MemberShip memberShip = (MemberShip) result.get(0)[1];

        PaymentInfoDTO paymentInfoDTO = entityToDTO(payment, memberShip);

        paymentInfoDTO.calculateTime(memberShip.getMonth());

        return paymentInfoDTO;

    }
}
