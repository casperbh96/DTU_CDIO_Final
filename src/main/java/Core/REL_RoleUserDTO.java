package main.java.Core;

public class REL_RoleUserDTO {

    private int userId;
    private int roleId;

    public REL_RoleUserDTO(int userId, int roleId) {
        this.userId = userId;
        this.roleId = roleId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "REL_RoleUserDTO{" +
                "userId=" + userId +
                ", roleId=" + roleId +
                '}';
    }
}
