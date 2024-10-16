package entity.enum_package;

public enum ShipperStatus {
    active("Active"),
    inActive("InActive");

    private String description;
    // Constructor
    ShipperStatus(String description) {
        this.description = description;
    }

    // Getter cho mô tả
    public String getDescription() {
        return description;
    }
}
