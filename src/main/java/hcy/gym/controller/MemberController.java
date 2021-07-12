package hcy.gym.controller;

import hcy.gym.dto.member.MemberSaveDTO;
import hcy.gym.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;

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

}
