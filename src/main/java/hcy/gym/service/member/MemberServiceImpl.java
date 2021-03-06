package hcy.gym.service.member;

import hcy.gym.domain.Member;
import hcy.gym.dto.member.MemberResponseDTO;
import hcy.gym.dto.member.MemberSaveDTO;
import hcy.gym.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    @Transactional
    @Override
    public void join(MemberSaveDTO memberSaveDTO) {

        log.info("회원가입 : {}", memberSaveDTO);

        Member member = dtoToEntity(memberSaveDTO);
        memberRepository.save(member);

    }

    @Override
    public MemberResponseDTO login(String loginId, String password) {

        log.info("로그인 id : {}", loginId);

        Optional<Member> result = memberRepository.findByLoginIdAndPassword(loginId, password);

        return !result.isEmpty() ? entityToDTO(result.get()) : null;

    }
}
