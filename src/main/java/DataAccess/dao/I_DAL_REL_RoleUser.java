package main.java.DataAccess.dao;

import main.java.Core.REL_RoleUserDTO;

import java.sql.SQLException;
import java.util.List;

public interface I_DAL_REL_RoleUser {

    REL_RoleUserDTO createSingleUserRole( REL_RoleUserDTO singleUserRole ) throws SQLException;
    List<REL_RoleUserDTO> createMultipleUserRoles( List<REL_RoleUserDTO> listOfUserRoles ) throws SQLException;

    REL_RoleUserDTO readSingleUserRolebyId(int userRoleId ) throws SQLException;
    List<REL_RoleUserDTO> readMultipleUserRolesByList (List<Integer> listOfUserRoleIds) throws SQLException;
    List<REL_RoleUserDTO> readUserRolebySearch(String keyword) throws SQLException;
    List<REL_RoleUserDTO> readAllUserRoles() throws SQLException;

    REL_RoleUserDTO updateSingleUserRole(REL_RoleUserDTO userRole) throws SQLException;
    List<REL_RoleUserDTO> updateMultipleUserRoles(List<REL_RoleUserDTO> listOfUserRoles) throws SQLException;

    REL_RoleUserDTO deleteSingleUserRole(int userRoleId) throws SQLException;
    REL_RoleUserDTO deleteMultipleUserRoles(List<Integer> listOfUserRoleIds) throws SQLException;
}
