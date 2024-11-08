package entity.enum_package;

public enum TypeDelivery {
    delivery("Delivery"),
    receive("Receive"),
    refund("Refund");
    private String description;
    // Constructor
    TypeDelivery(String description) {
        this.description = description;
    }

    // Getter cho mô tả
    public String getDescription() {
        return description;
    }
}
