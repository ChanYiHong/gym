package hcy.gym;

import hcy.gym.domain.Member;
import hcy.gym.domain.MemberShip;
import hcy.gym.repository.member.MemberRepository;
import hcy.gym.repository.membership.MemberShipRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class InitData {

    private final InitDataService initDataService;

    @PostConstruct
    public void init() {
        initDataService.initMember();
        initDataService.initMemberShip();
    }

    @Component
    @RequiredArgsConstructor
    static class InitDataService {

        private final MemberRepository memberRepository;
        private final MemberShipRepository memberShipRepository;


        @Transactional
        void initMember() {
            Member member = Member.builder()
                    .loginId("test")
                    .name("test1")
                    .password("1234")
                    .phoneNumber("01012345678")
                    .build();
            memberRepository.save(member);
        }


        @Transactional
        void initMemberShip() {

            memberShipRepository.save(MemberShip.builder()
                    .name("주 5회 1개월")
                    .month(1)
                    .week(5)
                    .price(330000)
                    .build());

            memberShipRepository.save(MemberShip.builder()
                    .name("주 5회 3개월")
                    .month(3)
                    .week(5)
                    .price(495000)
                    .build());

            memberShipRepository.save(MemberShip.builder()
                    .name("주 5회 6개월")
                    .month(6)
                    .week(5)
                    .price(759000)
                    .build());

            memberShipRepository.save(MemberShip.builder()
                    .name("주 5회 12개월")
                    .month(12)
                    .week(5)
                    .price(1089000)
                    .build());

            memberShipRepository.save(MemberShip.builder()
                    .name("주 2회 1개월")
                    .month(1)
                    .week(2)
                    .price(275000)
                    .build());

            memberShipRepository.save(MemberShip.builder()
                    .name("주 2회 3개월")
                    .month(3)
                    .week(2)
                    .price(319000)
                    .build());

            memberShipRepository.save(MemberShip.builder()
                    .name("주 2회 6개월")
                    .month(6)
                    .week(2)
                    .price(495000)
                    .build());

            memberShipRepository.save(MemberShip.builder()
                    .name("주 2회 12개월")
                    .month(12)
                    .week(2)
                    .price(726000)
                    .build());

            memberShipRepository.save(MemberShip.builder()
                    .name("주 3회 1개월")
                    .month(6)
                    .week(2)
                    .price(297000)
                    .build());

            memberShipRepository.save(MemberShip.builder()
                    .name("주 3회 3개월")
                    .month(3)
                    .week(3)
                    .price(396000)
                    .build());

            memberShipRepository.save(MemberShip.builder()
                    .name("주 3회 6개월")
                    .month(6)
                    .week(3)
                    .price(539000)
                    .build());

            memberShipRepository.save(MemberShip.builder()
                    .name("주 3회 12개월")
                    .month(12)
                    .week(3)
                    .price(869000)
                    .build());

        }
    }
}
