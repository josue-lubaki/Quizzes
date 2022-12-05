package ca.josue.mainactivity.utils;

public enum Menu {
    HOME(1),
    PREGAME(2),
    PROFILE(3);

    private final int id;

    Menu(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
