package hcy.gym.controller;

import hcy.gym.argumentresolver.Login;
import hcy.gym.dto.member.MemberResponseDTO;
import hcy.gym.dto.member.MemberSaveDTO;
import hcy.gym.dto.payment.PaymentInfoDTO;
import hcy.gym.dto.reservation.ReservationResponseDTO;
import hcy.gym.service.member.MemberService;
import hcy.gym.service.payment.PaymentService;
import hcy.gym.service.reservation.ReservationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;
    private final PaymentService paymentService;
    private final ReservationService reservationService;

    @ModelAttribute("memberResponseDTO")
    public MemberResponseDTO memberResponseDTO(@Login MemberResponseDTO loginMember) {
        return loginMember;
    }

    @GetMapping("/join")
    public String joinForm(@ModelAttribute MemberSaveDTO memberSaveDTO) {
        return "members/join";
    }

    @PostMapping("/join")
    public String join(@Validated @ModelAttribute MemberSaveDTO memberSaveDTO, BindingResult bindingResult) {

        // 비밀번호 (너무 단순할 때 체크)
        if (memberSaveDTO.getPassword() != null) {
            String password = memberSaveDTO.getPassword();
            // 3글자 이하인 경우.
            if (password.length() < 4) {
                bindingResult.reject("passwordLength");
            }
        }

        // 핸드폰 번호 (추후 고도화 예정)
        if (memberSaveDTO.getPhoneNumber() != null) {
            String phoneNumber = memberSaveDTO.getPhoneNumber();
            // 11자리가 아닌 경우.
            if (phoneNumber.length() != 11) {
                bindingResult.reject("phoneNumberLength");
            }
        }

        if (bindingResult.hasErrors()) {
            log.info("회원가입 error : {}", bindingResult);
            return "members/join";
        }

        log.info("회원 가입 POST 요청 : {}", memberSaveDTO);
        memberService.join(memberSaveDTO);
        return "redirect:/";
    }

    // 회원 정보 보기
    @GetMapping("/members/{id}")
    public String memberInfo(@PathVariable("id") Long memberId, Model model) {

        log.info("회원 정보 조회 : {}", memberId);

        // 멤버쉽 결제 정보
        PaymentInfoDTO paymentInfo = paymentService.getByMemberId(memberId);
        model.addAttribute("payment", paymentInfo);

        // 수업 예약 정보
        List<ReservationResponseDTO> reservations = reservationService.getListByMemberId(memberId);
        model.addAttribute("reservations", reservations);

        return "members/info";

    }

}
