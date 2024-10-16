package entity.enum_package;

public enum DocumentType {
    file("Loại file"),
    image("Loai hiình ảnh");

    private String description;

    // Constructor
    DocumentType(String description) {
        this.description = description;
    }

    // Getter cho mô tả
    public String getDescription() {
        return description;
    }
}
