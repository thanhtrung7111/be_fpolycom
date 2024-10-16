package entity.enum_package;

public enum TypeNotifycationStore {
    product("Product"),
    user("User");
    private String description;
    // Constructor
    TypeNotifycationStore(String description) {
        this.description = description;
    }

    // Getter cho mô tả
    public String getDescription() {
        return description;
    }
}
