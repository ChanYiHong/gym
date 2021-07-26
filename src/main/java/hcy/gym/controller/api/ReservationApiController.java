package hcy.gym.controller.api;

import hcy.gym.argumentresolver.Login;
import hcy.gym.dto.member.MemberResponseDTO;
import hcy.gym.dto.reservation.ReservationRequestDTO;
import hcy.gym.service.reservation.ReservationService;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reservations/api")
@Slf4j
public class ReservationApiController {

    private final ReservationService reservationService;

    @PostMapping
    public ResponseEntity<String> reserveClass(@RequestBody ReservationList reservationList,
                                               @Login MemberResponseDTO loginMember){

        log.info("수업 예약 시작!");

        String result = reservationList.toString();
        List<ReservationRequestDTO> reservationRequestDTOList = reservationList.getClassInfoList();
        for (ReservationRequestDTO reservationRequestDTO : reservationRequestDTOList) {
            log.info("{}", reservationRequestDTO);
        }


        reservationRequestDTOList.stream()
                .forEach(reservationRequestDTO -> {
                    reservationRequestDTO.setMemberId(loginMember.getId());
                    log.info("수업 예약 요청 : {}", reservationRequestDTO);
                });

        reservationService.reserve(reservationRequestDTOList);

        log.info("수업 예약 완료!");

        return new ResponseEntity<>(HttpStatus.OK);

    }

    /** 이름으로 맞춰서 binding하기 때문에 view의 변수 명이랑 controller의 변수명을 똑같이 해결해야 한다. **/
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    static class ReservationList {
        private List<ReservationRequestDTO> classInfoList;
    }

}
