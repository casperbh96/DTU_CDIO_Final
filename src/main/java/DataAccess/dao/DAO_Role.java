package DataAccess.dao;

import DataAccess.dto.RoleDTO;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

import static DataAccess.dao.Connector.static_commitTransAction;
import static DataAccess.dao.Connector.static_createConnection;
import static DataAccess.dao.Connector.static_startTransAction;
import static DataAccess.dao.Connector.*;

public class DAO_Role implements I_DAL_Role {
    @Override
    public RoleDTO createSingleRole(RoleDTO singleRole) {
        return null;
    }

    @Override
    public List<RoleDTO> createMultipleRoles(List<RoleDTO> listOfRoles) {
        return null;
    }

    @Override
    public RoleDTO readSingleRolebyId(int roleId) {
        return null;
    }

    @Override
    public List<RoleDTO> readMultipleRolesByList(List<Integer> listOfRoleIds) {
        return null;
    }

    @Override
    public List<RoleDTO> readRolebySearch(String keyword) {
        return null;
    }

    @Override
    public List<RoleDTO> readAllRoles() {
        return null;
    }

    @Override
    public RoleDTO updateSingleRole(RoleDTO role) {
        return null;
    }

    @Override
    public List<RoleDTO> updateMultipleRoles(List<RoleDTO> listOfRoles) {
        return null;
    }

    @Override
    public RoleDTO deleteSingleRole(RoleDTO role) {
        return null;
    }

    @Override
    public List<RoleDTO> deleteMultipleRoles(List<RoleDTO> listOfRoles) {
        return null;
    }
}
