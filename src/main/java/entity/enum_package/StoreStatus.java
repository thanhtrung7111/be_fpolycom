package entity.enum_package;

public enum StoreStatus {
    pending("Pending"),
    active("Active"),
    rejected("Rejected"),
    lock("Lock");

    private String description;
    // Constructor
    StoreStatus(String description) {
        this.description = description;
    }

    // Getter cho mô tả
    public String getDescription() {
        return description;
    }
}
