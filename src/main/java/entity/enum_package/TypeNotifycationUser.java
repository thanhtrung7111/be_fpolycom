package entity.enum_package;

public enum TypeNotifycationUser {
    evaluate("Evaluate"),
    voucher("Voucher"),
    product("Product");
    private String description;
    // Constructor
    TypeNotifycationUser(String description) {
        this.description = description;
    }

    // Getter cho mô tả
    public String getDescription() {
        return description;
    }
}
