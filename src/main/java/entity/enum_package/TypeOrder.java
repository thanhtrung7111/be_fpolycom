package entity.enum_package;

public enum TypeOrder {
    gift("Gift");
    private String description;
    // Constructor
    TypeOrder(String description) {
        this.description = description;
    }

    // Getter cho mô tả
    public String getDescription() {
        return description;
    }

}
