package hcy.gym.service.membership;

import hcy.gym.domain.MemberShip;
import hcy.gym.dto.membership.MemberShipResponseDTO;
import hcy.gym.repository.membership.MemberShipRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberShipServiceImpl implements MemberShipService{

    private final MemberShipRepository memberShipRepository;

    @Override
    public List<MemberShipResponseDTO> getAll() {
        List<MemberShip> result = memberShipRepository.findAll();
        return result.stream().map(this::entityToDTO).collect(Collectors.toList());
    }

    @Override
    public MemberShipResponseDTO getOne(Long id) {
        MemberShip memberShip = memberShipRepository.findById(id)
                .orElseThrow();

        return entityToDTO(memberShip);
    }
}
