package main.java.BusinessLogic;

import main.java.Core.REL_RoleUserDTO;

import java.sql.SQLException;
import java.util.List;

public interface I_BLLRoleUser {
    boolean assignUserRole(REL_RoleUserDTO singleUserRole) throws SQLException;
    boolean assignUserMultipleRoles(List<REL_RoleUserDTO> listOfUserRoles ) throws SQLException;

    boolean doesUserHaveRole(int userId, int roleId) throws SQLException;
    List<REL_RoleUserDTO> readUsersRoles(int user_id) throws SQLException;
    List<REL_RoleUserDTO> readAllUserRoles() throws SQLException;

    void deleteUserRole(int userId, int roleId) throws SQLException;
    void deleteMultipleUserRole(List<REL_RoleUserDTO> userRoles) throws SQLException;
}
