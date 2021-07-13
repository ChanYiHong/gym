package hcy.gym.controller;

import hcy.gym.SessionConst;
import hcy.gym.dto.login.LoginRequestDTO;
import hcy.gym.dto.member.MemberResponseDTO;
import hcy.gym.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@Slf4j
public class LoginController {

    private final MemberService memberService;

    @GetMapping("/login")
    public String loginForm(@ModelAttribute LoginRequestDTO loginRequestDTO) {
        return "login/loginForm";
    }

    @PostMapping("/login")
    public String login(@Validated @ModelAttribute LoginRequestDTO loginRequestDTO,
                        BindingResult bindingResult, HttpServletRequest request) {

        if(bindingResult.hasErrors()) {
            log.info("로그인 에러 : {}", bindingResult);
            return "login/loginForm";
        }

        MemberResponseDTO loginMember = memberService.login(loginRequestDTO.getLoginId(), loginRequestDTO.getPassword());

        if (loginMember == null) {
            log.info("회원 찾을 수 없음");
            bindingResult.reject("loginFail");
            return "login/loginForm";
        }

        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);

        return "redirect:/";

    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }

}
