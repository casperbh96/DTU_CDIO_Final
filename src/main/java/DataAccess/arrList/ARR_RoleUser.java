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
        for(REL_RoleUserDTO roleU : listOfUserRoles){
            roleUsers.add(roleU);
        }
        return true;
    }

    @Override
    public boolean doesUserHaveRole(int userId, int roleId) throws SQLException {
        for(REL_RoleUserDTO roleU : roleUsers){
            if(roleU.getUserId() == userId && roleU.getRoleId() == roleId){
                return true;
            }
        }
        return false;
    }

    @Override
    public List<REL_RoleUserDTO> readUsersRoles(int user_id) throws SQLException {
        List<REL_RoleUserDTO> roleUserList = new ArrayList<>();
        for(REL_RoleUserDTO roleU : roleUsers){
            if(roleU.getUserId() == user_id){
                roleUserList.add(roleU);
            }
        }
        return roleUserList;
    }

    @Override
    public List<REL_RoleUserDTO> readAllUserRoles() throws SQLException {
        return roleUsers;
    }

    @Override
    public void deleteUserRole(int userId, int roleId) throws SQLException {
        REL_RoleUserDTO toRemove = null;
        for(REL_RoleUserDTO roleU : roleUsers){
            if(userId == roleU.getUserId() && roleId == roleU.getRoleId()){
                toRemove = roleU;
            }
        }
        if(toRemove != null){
            roleUsers.remove(toRemove);
        }
    }

    @Override
    public void deleteMultipleUserRole(List<REL_RoleUserDTO> userRoles) throws SQLException {
        ArrayList<REL_RoleUserDTO> updatedList = new ArrayList<>();

        for(REL_RoleUserDTO role : roleUsers){
            for(REL_RoleUserDTO r : userRoles){
                if(role != r){
                    updatedList.add(role);
                }
            }
        }

        roleUsers = updatedList;
    }
}
