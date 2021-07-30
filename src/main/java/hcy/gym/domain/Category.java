package hcy.gym.domain;

import lombok.Getter;

@Getter
public enum Category {

    QUESTION("질문"), FREE("자유"), HELLO("가입인사");

    private final String description;

    Category(String description) {
        this.description = description;
    }
}
