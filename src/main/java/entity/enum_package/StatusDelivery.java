package entity.enum_package;

public enum StatusDelivery {
    complete("Complete"),
    taking("Taking"),
    appoinment("Appoinment"),
    failed("Failed");


    private String description;
    // Constructor
    StatusDelivery(String description) {
        this.description = description;
    }

    // Getter cho mô tả
    public String getDescription() {
        return description;
    }
}
