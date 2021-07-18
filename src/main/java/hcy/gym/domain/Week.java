package hcy.gym.domain;

import lombok.Getter;

@Getter
public enum Week {

    MON("월"), TUE("화"), WED("수"), THU("목"), FRI("금");

    private final String name;

    Week(String name) {
        this.name = name;
    }
}
