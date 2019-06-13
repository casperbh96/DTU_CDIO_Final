package main.java.DataAccess.dao;

import main.java.Core.REL_RoleUserDTO;

import java.sql.SQLException;
import java.util.List;

public class DAO_REL_RoleUser implements I_DAL_REL_RoleUser {
    @Override
    public REL_RoleUserDTO createSingleUserRole(REL_RoleUserDTO singleUserRole) throws SQLException {
        return null;
    }

    @Override
    public List<REL_RoleUserDTO> createMultipleUserRoles(List<REL_RoleUserDTO> listOfUserRoles) throws SQLException {
        return null;
    }

    @Override
    public REL_RoleUserDTO readSingleUserRolebyId(int userRoleId) throws SQLException {
        return null;
    }

    @Override
    public List<REL_RoleUserDTO> readMultipleUserRolesByList(List<Integer> listOfUserRoleIds) throws SQLException {
        return null;
    }

    @Override
    public List<REL_RoleUserDTO> readUserRolebySearch(String keyword) throws SQLException {
        return null;
    }

    @Override
    public List<REL_RoleUserDTO> readAllUserRoles() throws SQLException {
        return null;
    }

    @Override
    public REL_RoleUserDTO updateSingleUserRole(REL_RoleUserDTO userRole) throws SQLException {
        return null;
    }

    @Override
    public List<REL_RoleUserDTO> updateMultipleUserRoles(List<REL_RoleUserDTO> listOfUserRoles) throws SQLException {
        return null;
    }

    @Override
    public REL_RoleUserDTO deleteSingleUserRole(int userRoleId) throws SQLException {
        return null;
    }

    @Override
    public REL_RoleUserDTO deleteMultipleUserRoles(List<Integer> listOfUserRoleIds) throws SQLException {
        return null;
    }
}
