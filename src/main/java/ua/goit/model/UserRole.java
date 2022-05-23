package ua.goit.model;

public enum UserRole {
    ROLE_ADMIN("ROLE_ADMIN"),
    ROLE_VISITOR("ROLE_VISITOR");

    private String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
