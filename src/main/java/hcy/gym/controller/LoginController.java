package hcy.gym.controller;

import hcy.gym.dto.member.MemberSaveDTO;
import hcy.gym.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class LoginController {

    private final MemberService memberService;

    @GetMapping("/join")
    public String joinForm(@ModelAttribute MemberSaveDTO memberSaveDTO) {
        return "members/join";
    }

    @PostMapping("/join")
    public String join(@ModelAttribute MemberSaveDTO memberSaveDTO) {
        log.info("회원 가입 POST 요청 : {}", memberSaveDTO);
        memberService.join(memberSaveDTO);
        return "redirect:/";
    }

}
