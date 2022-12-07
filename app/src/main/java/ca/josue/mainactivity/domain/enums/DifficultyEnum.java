package ca.josue.mainactivity.domain.enums;

public enum DifficultyEnum {
    EASY("Easy"),
    MEDIUM("Medium"),
    HARD("Hard");

    private final String name;

    DifficultyEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

