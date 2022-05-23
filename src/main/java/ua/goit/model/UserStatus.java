package ua.goit.model;

public enum UserStatus {
    ACTIVE("ACTIVE"),
    DISABLED("DISABLED");

    private String status;

    UserStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
