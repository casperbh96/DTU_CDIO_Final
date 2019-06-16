package main.java.BusinessLogic;

import main.java.Core.RoleDTO;

import java.sql.SQLException;
import java.util.List;

public interface I_BLLRole {
    RoleDTO createRole(RoleDTO singleRole ) throws SQLException;
    List<RoleDTO> createRoles(List<RoleDTO> listOfRoles ) throws SQLException;

    RoleDTO getRoleById(int roleId ) throws SQLException;
    List<RoleDTO> getRolesByList (List<Integer> listOfRoleIds) throws SQLException;
    List<RoleDTO> getRolebySearch(String keyword) throws SQLException;
    List<RoleDTO> getAllRoles() throws SQLException;

    RoleDTO updateRole(RoleDTO role) throws SQLException;
    List<RoleDTO> updateRoles(List<RoleDTO> listOfRoles) throws SQLException;
}
