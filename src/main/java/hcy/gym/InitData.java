package hcy.gym;

import hcy.gym.domain.Member;
import hcy.gym.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

@Component
@RequiredArgsConstructor
public class InitData {

    private final InitDataService initDataService;

    @PostConstruct
    public void init() {
        initDataService.initMember();
    }

    @Component
    @RequiredArgsConstructor
    static class InitDataService {

        private final MemberRepository memberRepository;

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

    }

}
