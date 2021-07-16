package hcy.gym.domain;

import lombok.Getter;

@Getter
public enum PaymentWay {

    CARD("카드"), CASH("현금");

    private final String description;

    PaymentWay(String description) {
        this.description = description;
    }
}
