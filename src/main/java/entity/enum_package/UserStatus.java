package entity.enum_package;

public enum UserStatus {
    active("Active"),
    pending("Pending"),
    lock("Lock");
    private String description;
    // Constructor
    UserStatus(String description) {
        this.description = description;
    }

    // Getter cho mô tả
    public String getDescription() {
        return description;
    }
}
