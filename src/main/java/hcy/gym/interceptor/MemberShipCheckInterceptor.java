package hcy.gym.interceptor;

import hcy.gym.SessionConst;
import hcy.gym.dto.member.MemberResponseDTO;
import hcy.gym.dto.payment.PaymentInfoDTO;
import hcy.gym.service.payment.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

/** 아직 멤버쉽 결제를 하지 않은 회원이, 운동 예약을 시도할 때 Interceptor 발동 **/
@Slf4j
@RequiredArgsConstructor
public class MemberShipCheckInterceptor implements HandlerInterceptor {

    private final PaymentService paymentService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String requestURI = request.getRequestURI();
        log.info("MemberShipCheckInterceptor request URI : {}", requestURI);

        HttpSession session = request.getSession(false);

        MemberResponseDTO loginMember = (MemberResponseDTO) session.getAttribute(SessionConst.LOGIN_MEMBER);

        Long memberId = loginMember.getId();

        PaymentInfoDTO payment = paymentService.getByMemberId(memberId);

        if (payment == null) {
            log.info("MemberShip 가입이 필요한 Member ... {}", loginMember);

            response.setContentType("text/html; charset=UTF-8");

            PrintWriter out = response.getWriter();

            out.println("<script>alert('멤버쉽 가입이 필요합니다! 멤버쉽 가입 페이지로 이동합니다!'); location.href='/memberShips';</script>");

            out.flush();

            return false;
        }

        return true;

    }
}
