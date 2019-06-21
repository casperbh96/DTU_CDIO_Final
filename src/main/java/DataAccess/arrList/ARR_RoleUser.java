package main.java.DataAccess.arrList;

import main.java.Core.REL_RoleUserDTO;
import main.java.DataAccess.dao.I_DAL_REL_RoleUser;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ARR_RoleUser implements I_DAL_REL_RoleUser {
    ArrayList<REL_RoleUserDTO> roleUsers;

    public ARR_RoleUser(){
        roleUsers = new ArrayList<>();
        roleUsers.add(new REL_RoleUserDTO(1, 1));
        roleUsers.add(new REL_RoleUserDTO(1, 2));
        roleUsers.add(new REL_RoleUserDTO(1, 3));
        roleUsers.add(new REL_RoleUserDTO(1, 4));
        roleUsers.add(new REL_RoleUserDTO(2, 1));
        roleUsers.add(new REL_RoleUserDTO(3, 2));
        roleUsers.add(new REL_RoleUserDTO(3, 3));
        roleUsers.add(new REL_RoleUserDTO(4, 4));
        roleUsers.add(new REL_RoleUserDTO(4, 2));
    }

    @Override
    public boolean assignUserRole(REL_RoleUserDTO singleUserRole) throws SQLException {
        roleUsers.add(singleUserRole);
        return true;
    }

    @Override
    public boolean assignUserMultipleRoles(List<REL_RoleUserDTO> listOfUserRoles) throws SQLException {
        List<Integer> listOfIds = new ArrayList<>();
        for(REL_RoleUserDTO roleU : listOfUserRoles){
            roleUsers.add(roleU);
        }
        return true;
    }

    @Override
    public boolean doesUserHaveRole(int userId, int roleId) throws SQLException {
        return false;
    }

    @Override
    public List<REL_RoleUserDTO> readUsersRoles(int user_id) throws SQLException {
        return null;
    }

    @Override
    public List<REL_RoleUserDTO> readAllUserRoles() throws SQLException {
        return null;
    }

    @Override
    public void deleteUserRole(int userId, int roleId) throws SQLException {

    }

    @Override
    public void deleteMultipleUserRole(List<REL_RoleUserDTO> userRoles) throws SQLException {

    }
}
