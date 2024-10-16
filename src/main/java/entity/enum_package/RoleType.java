package entity.enum_package;

public enum RoleType {
    USER("User"),
    ADMIN("Admin"),
    STORE("Store"),
    SHIPPER("Shipper");

    private String description;
    // Constructor
    RoleType(String description) {
        this.description = description;
    }

    // Getter cho mô tả
    public String getDescription() {
        return description;
    }
}
