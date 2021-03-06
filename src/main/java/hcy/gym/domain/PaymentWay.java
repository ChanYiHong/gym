package hcy.gym.domain;

import lombok.Getter;

@Getter
public enum PaymentWay {

    CARD("์นด๋"), CASH("ํ๊ธ");

    private final String description;

    PaymentWay(String description) {
        this.description = description;
    }
}
