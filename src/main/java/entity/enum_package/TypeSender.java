package entity.enum_package;

public enum TypeSender {
    store("store"),
    user("user"),
    shipper("shipper");
    private String description;
    // Constructor
    TypeSender(String description) {
        this.description = description;
    }

    // Getter cho mô tả
    public String getDescription() {
        return description;
    }
}
