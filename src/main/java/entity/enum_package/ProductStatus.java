package entity.enum_package;

public enum ProductStatus {
    pending("Pending"),
    active("Active"),
    inActive("InActive");

    private String description;
    // Constructor
    ProductStatus(String description) {
        this.description = description;
    }

    // Getter cho mô tả
    public String getDescription() {
        return description;
    }
}
