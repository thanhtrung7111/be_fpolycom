package entity.enum_package;

public enum BankStatus {
    active("Đang hoạt ộng"),
    inActive("Khong hoat dong");

    private String description;

    // Constructor
    BankStatus(String description) {
        this.description = description;
    }

    // Getter cho mô tả
    public String getDescription() {
        return description;
    }
}
