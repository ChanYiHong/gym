package hcy.gym.controller;

import hcy.gym.argumentresolver.Login;
import hcy.gym.dto.member.MemberResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
@RequiredArgsConstructor
@Slf4j
public class HomeController {

    @ModelAttribute("memberResponseDTO")
    public MemberResponseDTO memberResponseDTO(@Login MemberResponseDTO memberResponseDTO) {
        return memberResponseDTO;
    }


    @GetMapping("/")
    public String home() {
        return "index";
    }

}
