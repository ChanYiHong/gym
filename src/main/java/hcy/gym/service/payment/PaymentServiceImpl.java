package hcy.gym.service.payment;

import hcy.gym.domain.Member;
import hcy.gym.domain.MemberShip;
import hcy.gym.domain.Payment;
import hcy.gym.dto.member.MemberResponseDTO;
import hcy.gym.dto.membership.MemberShipRegisterDTO;
import hcy.gym.repository.member.MemberRepository;
import hcy.gym.repository.membership.MemberShipRepository;
import hcy.gym.repository.payment.PaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
