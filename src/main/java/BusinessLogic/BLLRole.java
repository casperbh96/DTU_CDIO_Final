package main.java.BusinessLogic;

import main.java.Core.RoleDTO;
import main.java.DataAccess.dao.DAO_Role;
import main.java.DataAccess.dao.I_DAL_Role;

import java.sql.SQLException;
import java.util.List;

public class BLLRole implements I_BLLRole {
    private I_DAL_Role DAL_role = new DAO_Role();

    @Override
    public RoleDTO createRole(RoleDTO singleRole) throws SQLException {
        return DAL_role.createSingleRole(singleRole);
    }

    @Override
    public List<RoleDTO> createRoles(List<RoleDTO> listOfRoles) throws SQLException {
        return DAL_role.createMultipleRoles(listOfRoles);
    }

    @Override
    public RoleDTO getRoleById(int roleId) throws SQLException {
        return DAL_role.readSingleRoleById(roleId);
    }

    @Override
    public List<RoleDTO> getRolesByList(List<Integer> listOfRoleIds) throws SQLException {
        return DAL_role.readMultipleRolesByList(listOfRoleIds);
    }

    @Override
    public List<RoleDTO> getRolebySearch(String keyword) throws SQLException {
        return DAL_role.readRoleBySearch(keyword);
    }

    @Override
    public List<RoleDTO> getAllRoles() throws SQLException {
        return DAL_role.readAllRoles();
    }

    @Override
    public RoleDTO updateRole(RoleDTO role) throws SQLException {
        return DAL_role.updateSingleRole(role);
    }

    @Override
    public List<RoleDTO> updateRoles(List<RoleDTO> listOfRoles) throws SQLException {
        return DAL_role.updateMultipleRoles(listOfRoles);
    }
}
