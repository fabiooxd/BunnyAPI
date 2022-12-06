package me.fabio.enums;

public enum Action {

    UPLOAD("PUT"),
    DOWNLOAD("GET"),
    DELETE("DELETE"),
    LIST("GET");

    private final String method;

    Action(String method) {
        this.method = method;
    }

    public String getMethod() {
        return method;
    }
}
