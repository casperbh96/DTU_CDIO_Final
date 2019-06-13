package main.java.DataAccess.dao;

import main.java.DataAccess.dto.RoleDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static main.java.DataAccess.dao.Connector.*;

public class DAO_Role implements I_DAL_Role {

    @Override
    public RoleDTO createSingleRole(RoleDTO singleRole) throws SQLException {
        return null;
    }

    @Override
    public List<RoleDTO> createMultipleRoles(List<RoleDTO> listOfRoles) throws SQLException {
        return null;
    }

    @Override
    public RoleDTO readSingleRolebyId(int roleId) throws SQLException {
        return null;
    }

    @Override
    public List<RoleDTO> readMultipleRolesByList(List<Integer> listOfRoleIds) throws SQLException {
        return null;
    }

    @Override
    public List<RoleDTO> readRolebySearch(String keyword) throws SQLException {
        return null;
    }

    @Override
    public List<RoleDTO> readAllRoles() throws SQLException {
        return null;
    }

    @Override
    public RoleDTO updateSingleRole(RoleDTO role) throws SQLException {
        return null;
    }

    @Override
    public List<RoleDTO> updateMultipleRoles(List<RoleDTO> listOfRoles) throws SQLException {
        return null;
    }
}
