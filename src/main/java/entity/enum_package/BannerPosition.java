package entity.enum_package;

public enum BannerPosition {
    top("Top"),
    bottom("Bottom"),
    right("Right"),
    left("Left"),
    middle("Middle"),
    slider("Slider");


    private String description;

    // Constructor
    BannerPosition(String description) {
        this.description = description;
    }

    // Getter cho mô tả
    public String getDescription() {
        return description;
    }
}
