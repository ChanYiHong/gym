package hcy.gym.domain;

import lombok.Getter;

@Getter
public enum PaymentPlan {

    TEMPORARY("일시불"), TWO_M("2개월 할부"), THREE_M("3개월 할부");

    private final String wayName;

    PaymentPlan(String wayName) {
        this.wayName = wayName;
    }

}
