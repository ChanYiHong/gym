package hcy.gym.service.payment;

import hcy.gym.domain.*;
import hcy.gym.dto.payment.PaymentInfoDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class PaymentServiceTest {

    @Autowired
    EntityManager em;

    @Autowired
    PaymentService paymentService;

    @Test
    @Rollback
    void getByMemberId() {

        Member member = Member.builder().loginId("test").name("test1").phoneNumber("01012341234").build();
        MemberShip memberShip = MemberShip.builder().month(6).week(1).name("temp").build();

        em.persist(member);
        em.persist(memberShip);

        Payment payment = Payment.builder().paymentPlan(PaymentPlan.TWO_M).paymentWay(PaymentWay.CARD)
                .member(member).memberShip(memberShip).build();

        em.persist(payment);

        PaymentInfoDTO result = paymentService.getByMemberId(member.getId());
        System.out.println(result);

    }
}