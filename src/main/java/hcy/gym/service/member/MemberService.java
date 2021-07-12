package hcy.gym.service.member;

import hcy.gym.domain.Member;
import hcy.gym.dto.member.MemberResponseDTO;
import hcy.gym.dto.member.MemberSaveDTO;

public interface MemberService {

    void join(MemberSaveDTO memberSaveDTO);

    MemberResponseDTO login(String loginId, String password);

    default Member dtoToEntity(MemberSaveDTO memberSaveDTO) {
        return Member.builder()
                .loginId(memberSaveDTO.getLoginId())
                .name(memberSaveDTO.getName())
                .password(memberSaveDTO.getPassword())
                .phoneNumber(memberSaveDTO.getPhoneNumber())
                .build();
    }

    default MemberResponseDTO entityToDTO(Member member) {
        return MemberResponseDTO.builder()
                .id(member.getId())
                .loginId(member.getLoginId())
                .name(member.getName())
                .password(member.getPassword())
                .phoneNumber(member.getPhoneNumber())
                .build();
    }

}
