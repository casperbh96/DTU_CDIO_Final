package main.java.DataAccess.dao;

import main.java.Core.RoleDTO;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

public interface I_DAL_Role {
    RoleDTO createSingleRole(RoleDTO singleRole ) throws SQLException;
    List<RoleDTO> createMultipleRoles(List<RoleDTO> listOfRoles ) throws SQLException;

    RoleDTO readSingleRoleById(int roleId ) throws SQLException;
    List<RoleDTO> readMultipleRolesByList (List<Integer> listOfRoleIds) throws SQLException;
    List<RoleDTO> readRoleBySearch(String keyword) throws SQLException;
    List<RoleDTO> readAllRoles() throws SQLException;

    RoleDTO updateSingleRole(RoleDTO role) throws SQLException;
    List<RoleDTO> updateMultipleRoles(List<RoleDTO> listOfRoles) throws SQLException;
}
