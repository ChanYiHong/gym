package hcy.gym.dto.payment;

import hcy.gym.domain.PaymentPlan;
import hcy.gym.domain.PaymentWay;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentInfoDTO {

    private String name;

    // 카드 or 현금.
    private PaymentWay paymentWay;

    // 할부.
    private PaymentPlan paymentPlan;

    // 멤버쉽 시작일.
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime startTime;

    // 멤버쉽 종료 예정일.
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime endTime;

    //== 남은 시간 계산 메서드 ==/
    public void calculateTime(Integer month) {
        // 현재 시간
        LocalDateTime now = LocalDateTime.now();
        // 마감 시간 계산
        this.endTime = this.startTime.plusMonths(month);
    }

}
