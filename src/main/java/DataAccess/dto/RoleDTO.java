package DataAccess.dto;

public class RoleDTO {

    private int roleId;
    private String rolename;

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public RoleDTO(int roleId, String rolename) {
        this.roleId = roleId;
        this.rolename = rolename;
    }

    @Override
    public String toString() {
        return "RoleDTO{" +
                "roleId=" + roleId +
                ", rolename='" + rolename + '\'' +
                '}';
    }
}
