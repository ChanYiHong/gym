package hcy.gym.service.reservation;

import hcy.gym.domain.Classes;
import hcy.gym.domain.Member;
import hcy.gym.domain.Reservation;
import hcy.gym.dto.reservation.ReservationRequestDTO;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationService {

    void reserve(List<ReservationRequestDTO> reservationRequestDTOList);

    default Reservation dtoToEntity(ReservationRequestDTO dto, Member member, Classes classes) {

        LocalDateTime classTime = LocalDateTime.of(dto.getYear(), dto.getMonth(),
                dto.getDay(), dto.getHour(), dto.getMinute());

        return Reservation.builder()
                .classTime(classTime)
                .member(member)
                .classes(classes)
                .build();
    }

}
