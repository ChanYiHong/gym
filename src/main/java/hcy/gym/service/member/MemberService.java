package hcy.gym.service.member;

import hcy.gym.domain.Member;
import hcy.gym.dto.member.MemberSaveDTO;

public interface MemberService {

    void join(MemberSaveDTO memberSaveDTO);

    default Member dtoToEntity(MemberSaveDTO memberSaveDTO) {
        return Member.builder()
                .loginId(memberSaveDTO.getLoginId())
                .name(memberSaveDTO.getName())
                .password(memberSaveDTO.getPassword())
                .phoneNumber(memberSaveDTO.getPhoneNumber())
                .build();
    }

}
