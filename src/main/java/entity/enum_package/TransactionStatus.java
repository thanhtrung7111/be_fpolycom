package entity.enum_package;

public enum TransactionStatus {
    pending("Pending"),
    declined("Declined"),
    complete("Complete");
    private String description;
    // Constructor
    TransactionStatus(String description) {
        this.description = description;
    }

    // Getter cho mô tả
    public String getDescription() {
        return description;
    }
}
