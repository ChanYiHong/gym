package hcy.gym.controller;

import hcy.gym.argumentresolver.Login;
import hcy.gym.dto.member.MemberResponseDTO;
import hcy.gym.dto.payment.HoldingRequestDTO;
import hcy.gym.dto.payment.PaymentInfoDTO;
import hcy.gym.service.payment.PaymentService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;

@Controller
@RequestMapping("/payments")
@RequiredArgsConstructor
@Slf4j
public class PaymentController {

    private final PaymentService paymentService;

    @ModelAttribute("memberResponseDTO")
    public MemberResponseDTO memberResponseDTO(@Login MemberResponseDTO loginMember) {
        return loginMember;
    }

    @GetMapping("/holding/{memberId}")
    public String holdingForm(@PathVariable("memberId") Long memberId,
                              @ModelAttribute HoldingRequestDTO holdingRequestDTO,
                              Model model) {

        PaymentInfoDTO result = paymentService.getByMemberId(memberId);
        model.addAttribute("dateDTO", new DateDTO(result.getStartTime(), result.getEndTime()));

        return "payments/holding";
    }

    @PostMapping("/holding/{memberId}")
    public String holdingPayment(@PathVariable Long memberId, @ModelAttribute HoldingRequestDTO holdingRequestDTO,
                                 RedirectAttributes redirectAttributes) {

        log.info("holding request : {} {}", memberId, holdingRequestDTO);

        paymentService.holding(memberId, holdingRequestDTO);

        redirectAttributes.addAttribute("memberId", memberId);

        return "redirect:/members/{memberId}";
    }

    @Data
    @AllArgsConstructor
    static class DateDTO {

        @DateTimeFormat(pattern = "yyyy-MM-dd")
        private LocalDate startTime;

        @DateTimeFormat(pattern = "yyyy-MM-dd")
        private LocalDate endTime;
    }

}
