package hcy.gym;

import hcy.gym.argumentresolver.LoginMemberArgumentResolver;
import hcy.gym.interceptor.LoginCheckInterceptor;
import hcy.gym.interceptor.MemberShipCheckInterceptor;
import hcy.gym.service.payment.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final PaymentService paymentService;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginCheckInterceptor())
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns("/", "/css/**", "/img/**", "/js/**", "/*.ico"
                        , "/error", "/login", "/join", "/memberships", "/infoList", "/reservations/api/**",
                        "/way");

        registry.addInterceptor(new MemberShipCheckInterceptor(paymentService))
                .order(2)
                .addPathPatterns("/classes");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new LoginMemberArgumentResolver());
    }
}
