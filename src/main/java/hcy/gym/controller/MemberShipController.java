package hcy.gym.controller;

import hcy.gym.argumentresolver.Login;
import hcy.gym.dto.member.MemberResponseDTO;
import hcy.gym.dto.membership.MemberShipRegisterDTO;
import hcy.gym.dto.membership.MemberShipResponseDTO;
import hcy.gym.service.membership.MemberShipService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/memberShips")
@Slf4j
public class MemberShipController {

    private final MemberShipService memberShipService;

    @ModelAttribute("memberResponseDTO")
    public MemberResponseDTO memberResponseDTO(@Login MemberResponseDTO memberResponseDTO) {
        return memberResponseDTO;
    }

    @GetMapping
    public String memberShipList(Model model) {

        log.info("membershipList");

        List<MemberShipResponseDTO> result = memberShipService.getAll();
        model.addAttribute("memberShips", result);
        return "memberships/list";
    }

    @GetMapping("/{id}")
    public String memberShipRegisterForm(@PathVariable("id") Long id,
                                         @ModelAttribute("memberShipRegisterDTO") MemberShipRegisterDTO memberShipRegisterDTO,
                                         Model model) {

        log.info("membership id : {}", id);

        MemberShipResponseDTO result = memberShipService.getOne(id);
        model.addAttribute("memberShip", result);

        return "memberships/register";
    }

}
