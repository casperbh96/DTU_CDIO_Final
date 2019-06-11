package DataAccess.dao;

import DataAccess.dto.RoleDTO;

import java.sql.*;
import java.util.ArrayList;
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
    public List<RoleDTO> readMultipleRolesByList(List<Integer> listOfRoleIds) throws SQLException {
        RoleDTO res;
        List<RoleDTO> roleList = new ArrayList<>();

        // Produce string with number of ? equal to size of listOfResourceIds
        String parameters = static_parameterBuilder(listOfRoleIds);

        try (Connection conn = static_createConnection()) {
            // Turns into SELECT * FROM resources WHERE resource_id IN () with the string builder in the parenthesis
            PreparedStatement pStmt = conn.prepareStatement("SELECT * FROM roles WHERE role_id IN (" + parameters + ")");

            // Set each of the ? to the corresponding Id from listOfRoleIds
//            int index = 1;
//            for (int i : listOfRoleIds) {
//                pStmt.setInt(index++, i);
//            }

            static_setIntPreparedStatements(pStmt, listOfRoleIds);      //TODO virker dette??

            ResultSet resultset = pStmt.executeQuery();

            while (resultset.next()) {
                res = new RoleDTO(resultset.getInt(1), resultset.getString(2));
                roleList.add(res);
            }
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }
        return roleList;
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
