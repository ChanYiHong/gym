package hcy.gym.service.membership;

import hcy.gym.domain.MemberShip;
import hcy.gym.dto.membership.MemberShipResponseDTO;

import java.util.List;

public interface MemberShipService {

    // 멤버쉽 리스트용
    List<MemberShipResponseDTO> getAll();

    // 멤버쉽 정보 및 결제화면용
    MemberShipResponseDTO getOne(Long id);



    default MemberShipResponseDTO entityToDTO(MemberShip memberShip) {
        return MemberShipResponseDTO.builder()
                .id(memberShip.getId())
                .name(memberShip.getName())
                .price(memberShip.getPrice())
                .month(memberShip.getMonth())
                .week(memberShip.getWeek())
                .build();
    }

}
