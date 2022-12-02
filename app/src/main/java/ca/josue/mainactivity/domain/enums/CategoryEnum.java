package ca.josue.mainactivity.domain.enums;

public enum CategoryEnum {
    SQL("SQL"),
    DOCKER("Docker"),
    HTML("HTML"),
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

    public String getName() {
        return name;
    }
}
