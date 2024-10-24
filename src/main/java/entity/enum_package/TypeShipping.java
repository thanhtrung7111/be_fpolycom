package entity.enum_package;

public enum TypeShipping {
    inner("Inner"),
    outer("Outer");
    private String description;
    // Constructor
    TypeShipping(String description) {
        this.description = description;
    }

    // Getter cho mô tả
    public String getDescription() {
        return description;
    }

}
