package hcy.gym.service.reservation;

import hcy.gym.domain.Classes;
import hcy.gym.domain.Member;
import hcy.gym.domain.Reservation;
import hcy.gym.domain.Teacher;
import hcy.gym.dto.reservation.ReservationRequestDTO;
import hcy.gym.dto.reservation.ReservationResponseDTO;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationService {

    // 요일 배열.
    String[] DAY_OF_WEEK = {"", "월", "화", "수", "목", "금", "토", "일"};

    void reserve(List<ReservationRequestDTO> reservationRequestDTOList);

    List<ReservationResponseDTO> getListByMemberId(Long memberId);

    default Reservation dtoToEntity(ReservationRequestDTO dto, Member member, Classes classes) {

        LocalDateTime classTime = LocalDateTime.of(dto.getYear(), dto.getMonth(),
                dto.getDay(), dto.getHour(), dto.getMinute());

        return Reservation.builder()
                .classTime(classTime)
                .member(member)
                .classes(classes)
                .build();
    }

    default ReservationResponseDTO entityToDTO(Reservation reservation, Classes classes) {

        return ReservationResponseDTO.builder()
                .id(reservation.getId())
                .year(reservation.getClassTime().getYear())
                .month(reservation.getClassTime().getMonthValue())
                .day(reservation.getClassTime().getDayOfMonth())
                .startTime(classes.getStartTime())
                .endTime(classes.getEndTime())
                .teacher(classes.getTeacher().getName())
                .dayOfWeek(DAY_OF_WEEK[reservation.getClassTime().getDayOfWeek().getValue()])
                .name(classes.getName())
                .build();

    }

}
