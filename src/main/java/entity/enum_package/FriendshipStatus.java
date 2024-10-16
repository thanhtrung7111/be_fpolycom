package entity.enum_package;

public enum FriendshipStatus {
    pending("Đang pending"),
    friendRequest("Friend request"),
    accepted("Accepted"),
    declined("Declined"),
    follow("Theo doi");
    private String description;

    // Constructor
    FriendshipStatus(String description) {
        this.description = description;
    }

    // Getter cho mô tả
    public String getDescription() {
        return description;
    }
}
