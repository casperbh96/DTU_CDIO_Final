package main.java.DataAccess.dao;

import main.java.DataAccess.dto.RoleDTO;

import java.beans.IntrospectionException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

public interface I_DAL_Role extends Serializable{


    RoleDTO createSingleRole( RoleDTO singleRole ) throws SQLException;
    List<RoleDTO> createMultipleRoles( List<RoleDTO> listOfRoles ) throws SQLException;

    RoleDTO readSingleRolebyId(int roleId ) throws SQLException;
    List<RoleDTO> readMultipleRolesByList (List<Integer> listOfRoleIds) throws SQLException;
    List<RoleDTO> readRolebySearch(String keyword) throws SQLException;
    List<RoleDTO> readAllRoles() throws SQLException;

    RoleDTO updateSingleRole(RoleDTO role) throws SQLException;
    List<RoleDTO> updateMultipleRoles(List<RoleDTO> listOfRoles) throws SQLException;

//    RoleDTO deleteSingleRole(RoleDTO role) throws SQLException;
//    List<RoleDTO> deleteMultipleRoles(List<RoleDTO> listOfRoles ) throws SQLException;


}
