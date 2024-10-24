package entity.enum_package;

public enum VoucherType {
    shipping("Shipping"),
    store("Store");
    private String description;
    // Constructor
    VoucherType(String description) {
        this.description = description;
    }

    // Getter cho mô tả
    public String getDescription() {
        return description;
    }
}
