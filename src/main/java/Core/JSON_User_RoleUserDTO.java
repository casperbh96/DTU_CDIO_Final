package main.java.Core;

import java.io.Serializable;

public class JSON_User_RoleUserDTO implements Serializable {
    private UserDTO user;
    private REL_RoleUserDTO roleUser;

    public JSON_User_RoleUserDTO(UserDTO user, REL_RoleUserDTO roleUser) {
        this.user = user;
        this.roleUser = roleUser;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public REL_RoleUserDTO getRoleUser() {
        return roleUser;
    }

    public void setRoleUser(REL_RoleUserDTO roleUser) {
        this.roleUser = roleUser;
    }

    @Override
    public String toString() {
        return "JSON_User_RoleUserDTO{" +
                "user=" + user +
                ", roleUser=" + roleUser +
                '}';
    }
}
