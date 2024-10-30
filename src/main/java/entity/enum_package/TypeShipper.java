package entity.enum_package;

public enum TypeShipper {
    delivery("Delivery"),
    receive("Receive");


    private String description;
    // Constructor
    TypeShipper(String description) {
        this.description = description;
    }

    // Getter cho mô tả
    public String getDescription() {
        return description;
    }
}
