package hcy.gym.controller;

import hcy.gym.argumentresolver.Login;
import hcy.gym.domain.Info;
import hcy.gym.dto.info.InfoResponseDTO;
import hcy.gym.dto.member.MemberResponseDTO;
import hcy.gym.dto.page.PageRequestDTO;
import hcy.gym.dto.page.PageResponseDTO;
import hcy.gym.service.info.InfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
@RequiredArgsConstructor
public class InfoController {

    private final InfoService infoService;

    @ModelAttribute("memberResponseDTO")
    public MemberResponseDTO memberResponseDTO(@Login MemberResponseDTO loginMember) {
        return loginMember;
    }


    @GetMapping("/way")
    public String wayToCome() {
        return "info/wayToCome";
    }

    @GetMapping("/infoList")
    public String information(PageRequestDTO pageRequestDTO, Model model) {

        PageResponseDTO<Info, InfoResponseDTO> result = infoService.getList(pageRequestDTO);
        model.addAttribute("infos", result);

        return "info/list";
    }

}
