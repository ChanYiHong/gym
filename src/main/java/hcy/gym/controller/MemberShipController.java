package hcy.gym.controller;

import hcy.gym.argumentresolver.Login;
import hcy.gym.dto.member.MemberResponseDTO;
import hcy.gym.dto.membership.MemberShipRegisterDTO;
import hcy.gym.dto.membership.MemberShipResponseDTO;
import hcy.gym.service.membership.MemberShipService;
import hcy.gym.service.payment.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/memberShips")
@Slf4j
public class MemberShipController {

    private final MemberShipService memberShipService;
    private final PaymentService paymentService;

    @ModelAttribute("memberResponseDTO")
    public MemberResponseDTO memberResponseDTO(@Login MemberResponseDTO loginMember) {
        return loginMember;
    }

    // 멤버쉽 정보들
    @GetMapping
    public String memberShipList(Model model) {

        log.info("membershipList");

        List<MemberShipResponseDTO> result = memberShipService.getAll();
        model.addAttribute("memberShips", result);
        return "memberships/list";
    }

    // 멤버쉽 개별 정보 및 신청 화면.
    @GetMapping("/{id}")
    public String memberShipRegisterForm(@PathVariable("id") Long id,
                                         @ModelAttribute MemberShipRegisterDTO memberShipRegisterDTO,
                                         Model model) {

        log.info("membership id : {}", id);

        MemberShipResponseDTO result = memberShipService.getOne(id);
        model.addAttribute("memberShip", result);

        return "memberships/register";
    }

    // 멤버쉽 등록. (만약 이미 등록된 회원이면 x)
    @PostMapping("/{id}")
    public String memberShipRegister(@PathVariable("id") Long id, @Login MemberResponseDTO loginMember,
                                     @ModelAttribute MemberShipRegisterDTO memberShipRegisterDTO) {

        log.info("memberShip id : {}", id);
        log.info("memberShipRegisterDTO : {}", memberShipRegisterDTO);

        Long result = paymentService.register(loginMember, memberShipRegisterDTO, id);

        log.info("payment success : {}", id);

        return "redirect:/";

    }

}
