package hcy.gym.controller;

import hcy.gym.argumentresolver.Login;
import hcy.gym.dto.classes.ClassesResponseDTO;
import hcy.gym.dto.member.MemberResponseDTO;
import hcy.gym.dto.payment.PaymentInfoDTO;
import hcy.gym.service.classes.ClassesService;
import hcy.gym.service.membership.MemberShipService;
import hcy.gym.service.payment.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalTime;
import java.util.List;

@Controller
@RequestMapping("/classes")
@RequiredArgsConstructor
@Slf4j
public class ClassesController {

    private final ClassesService classesService;
    private final PaymentService paymentService;

    @ModelAttribute("memberResponseDTO")
    public MemberResponseDTO memberResponseDTO(@Login MemberResponseDTO loginMember) {
        return loginMember;
    }

    // 시간표 화면
    @GetMapping
    public String classes(@Login MemberResponseDTO loginMember, Model model) {

        // 1교시 부터 7교시
        List<ClassesResponseDTO> one = classesService.getByStartTime(LocalTime.of(9, 30));
        List<ClassesResponseDTO> two = classesService.getByStartTime(LocalTime.of(10, 35));
        List<ClassesResponseDTO> three = classesService.getByStartTime(LocalTime.of(11, 40));
        List<ClassesResponseDTO> four = classesService.getByStartTime(LocalTime.of(18, 00));
        List<ClassesResponseDTO> five = classesService.getByStartTime(LocalTime.of(19, 00));
        List<ClassesResponseDTO> six = classesService.getByStartTime(LocalTime.of(20, 00));
        List<ClassesResponseDTO> seven = classesService.getByStartTime(LocalTime.of(21, 00));

        model.addAttribute("one", one);
        model.addAttribute("two", two);
        model.addAttribute("three", three);
        model.addAttribute("four", four);
        model.addAttribute("five", five);
        model.addAttribute("six", six);
        model.addAttribute("seven", seven);

        // 멤버쉽 결제 정보
        PaymentInfoDTO paymentInfo = paymentService.getByMemberId(loginMember.getId());

        model.addAttribute("payment", paymentInfo);

        return "classes/table";
    }

}
