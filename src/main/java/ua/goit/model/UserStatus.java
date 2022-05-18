package ua.goit.model;

public enum UserStatus {
    ACTIVE("ACTIVE"),
    NOT_ACTIVE("NOT_ACTIVE");

    private String status;

    UserStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
