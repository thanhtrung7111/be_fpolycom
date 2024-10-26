package entity.enum_package;

public enum OrderStatus {
    prepare("Prepare"),
    pending("Pending"),
    complete("Complete"),
    delivery("Delivery"),
    pickup("Pickup");


    private String description;
    // Constructor
    OrderStatus(String description) {
        this.description = description;
    }

    // Getter cho mô tả
    public String getDescription() {
        return description;
    }
}
