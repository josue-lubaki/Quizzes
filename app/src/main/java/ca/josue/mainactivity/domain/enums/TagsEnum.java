package ca.josue.mainactivity.domain.enums;

public enum TagsEnum {
    DOCKER("Docker"),
    BASH("Bash"),
    LINUX("Linux"),
    DEVOPS("DevOps"),
    KUBERNETES("Kubernetes"),
    JAVASCRIPT("JavaScript"),
    MYSQL("MySQL"),
    PHP("PHP"),
    WORDPRESS("WordPress"),
    HTML("HTML"),
    LARAVEL("Laravel");

    private final String name;

    TagsEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
