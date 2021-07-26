package hcy.gym.service.reservation;

import hcy.gym.domain.Classes;
import hcy.gym.domain.Member;
import hcy.gym.domain.Reservation;
import hcy.gym.domain.Teacher;
import hcy.gym.dto.reservation.ReservationRequestDTO;
import hcy.gym.dto.reservation.ReservationResponseDTO;
import hcy.gym.repository.classes.ClassesRepository;
import hcy.gym.repository.member.MemberRepository;
import hcy.gym.repository.reservation.ReservationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final MemberRepository memberRepository;
    private final ClassesRepository classesRepository;

    @Override
    @Transactional
    public void reserve(List<ReservationRequestDTO> reservationRequestDTOList) {

        Member member = memberRepository.findById(reservationRequestDTOList.get(0).getMemberId()).orElseThrow();

        List<Reservation> reservations = reservationRequestDTOList.stream()
                .map(dto -> {
                    Classes classes = classesRepository.findById(dto.getClassId()).orElseThrow();
                    System.out.println(classes);
                    return dtoToEntity(dto, member, classes);
                }).collect(Collectors.toList());

        for (Reservation reservation : reservations) {
            System.out.println(reservation);
        }

        System.out.println(member);

        reservations.stream()
                .forEach(reservationRepository::save);

    }

    @Override
    public List<ReservationResponseDTO> getListByMemberId(Long memberId) {

        log.info("findReservationByMemberId : {}", memberId);

        List<Object[]> result = reservationRepository.findByMemberId(memberId);

        return result.stream().map(objects -> entityToDTO((Reservation) objects[0], (Classes) objects[1])).collect(Collectors.toList());

    }
}
