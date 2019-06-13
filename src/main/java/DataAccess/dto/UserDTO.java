package DataAccess.dto;

public class UserDTO {

    private int userId;
    private String username;
    private String initials;
    private boolean inactive;

    public UserDTO(int userId, String username, String initials, boolean inactive) {
        this.userId = userId;
        this.username = username;
        this.initials = initials;
        this.inactive = inactive;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getInitials() {
        return initials;
    }

    public void setInitials(String initials) {
        this.initials = initials;
    }

    public boolean getInactive() {
        return inactive;
    }

    public void setInactive(boolean inactive) {
        this.inactive = inactive;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", initials='" + initials + '\'' +
                ", inactive=" + inactive +
                '}';
    }
}
