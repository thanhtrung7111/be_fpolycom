package entity.enum_package;

public enum TypeTransaction {
    pay("Pay"),
    recharge("recharge"),
    withdraw("Withdraw");
    private String description;
    // Constructor
    TypeTransaction(String description) {
        this.description = description;
    }

    // Getter cho mô tả
    public String getDescription() {
        return description;
    }

}
