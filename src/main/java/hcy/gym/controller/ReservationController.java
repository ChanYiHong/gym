package hcy.gym.controller;

import hcy.gym.argumentresolver.Login;
import hcy.gym.dto.member.MemberResponseDTO;
import hcy.gym.dto.reservation.ReservationRequestDTO;
import hcy.gym.service.reservation.ReservationService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
//@RequestMapping("/reservations")
@RequiredArgsConstructor
@Slf4j
public class ReservationController {

    private final ReservationService reservationService;

//    @PostMapping
    public String reserveClass(HttpServletRequest request,
                               @Login MemberResponseDTO loginMember) {

        String classInfoList = request.getParameter("jsonData");

        log.info(classInfoList);

//        List<ReservationRequestDTO> reservationRequestDTOList = reservationList.getReservationRequestDTOList();
//
//        reservationRequestDTOList.forEach(
//                reservationRequestDTO -> System.out.println(reservationRequestDTO)
//        );
//

        return "redirect:/";

    }

    @Data
    @AllArgsConstructor
    static class ReservationList {
        private List<ReservationRequestDTO> reservationRequestDTOList;
    }

}
