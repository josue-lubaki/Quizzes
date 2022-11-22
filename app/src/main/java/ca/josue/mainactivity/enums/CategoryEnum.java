package ca.josue.mainactivity.enums;

public enum CategoryEnum {
    SQL("SQL"),
    DOCKER("Docker"),
    BASH("Bash"),
    LINUX("Linux"),
    CMS("CMS"),
    CODE("Code"),
    DEVOPS("DevOps"),
    UNCATEGORIZED("Uncategorized");

    private final String name;

    CategoryEnum(String name) {
        this.name = name;
    }
}
