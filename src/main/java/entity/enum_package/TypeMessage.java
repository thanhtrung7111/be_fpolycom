package entity.enum_package;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public enum TypeMessage {
    image("Tmage"),
    text("Text");
    private String description;

    // Constructor
    TypeMessage(String description) {
        this.description = description;
    }

    // Getter cho mô tả
    public String getDescription() {
        return description;
    }

    @Enumerated(EnumType.STRING)
    TypeMessage typeMessage;

}
