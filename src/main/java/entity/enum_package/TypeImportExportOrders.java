package entity.enum_package;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public enum TypeImportExportOrders {
    importOrders("importOrders"),
    exportOrders("exportOrders");

    private String description;
    // Constructor
    TypeImportExportOrders(String description) {
        this.description = description;
    }

    // Getter cho mô tả
    public String getDescription() {
        return description;
    }

    @Enumerated(EnumType.STRING)
    TypeImportExportOrders typeImportExportOrders;


}
