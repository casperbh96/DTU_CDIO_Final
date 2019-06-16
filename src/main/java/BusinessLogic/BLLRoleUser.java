package main.java.BusinessLogic;

import main.java.Core.REL_RoleUserDTO;
import main.java.DataAccess.dao.DAO_REL_RoleUser;
import main.java.DataAccess.dao.I_DAL_REL_RoleUser;

import java.sql.SQLException;
import java.util.List;

public class BLLRoleUser implements I_BLLRoleUser{
    I_DAL_REL_RoleUser DAL_roleUser = new DAO_REL_RoleUser();

    @Override
    public boolean assignUserRole(REL_RoleUserDTO singleUserRole) throws SQLException {
        return DAL_roleUser.assignUserRole(singleUserRole);
    }

    @Override
    public boolean assignUserMultipleRoles(List<REL_RoleUserDTO> listOfUserRoles) throws SQLException {
        return DAL_roleUser.assignUserMultipleRoles(listOfUserRoles);
    }

    @Override
    public boolean doesUserHaveRole(int userId, int roleId) throws SQLException {
        return DAL_roleUser.doesUserHaveRole(userId, roleId);
    }

    @Override
    public List<REL_RoleUserDTO> readUsersRoles(int user_id) throws SQLException {
        return DAL_roleUser.readUsersRoles(user_id);
    }

    @Override
    public List<REL_RoleUserDTO> readAllUserRoles() throws SQLException {
        return DAL_roleUser.readAllUserRoles();
    }

    @Override
    public void deleteUserRole(int userId, int roleId) throws SQLException {
        DAL_roleUser.deleteUserRole(userId, roleId);
    }

    @Override
    public void deleteMultipleUserRole(List<REL_RoleUserDTO> userRoles) throws SQLException {
        DAL_roleUser.deleteMultipleUserRole(userRoles);
    }
}
