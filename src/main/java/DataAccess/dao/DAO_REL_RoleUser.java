package main.java.DataAccess.dao;

import main.java.Core.REL_RoleUserDTO;
import main.java.Core.RoleDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static main.java.DataAccess.dao.Connector.static_createConnection;

public class DAO_REL_RoleUser implements I_DAL_REL_RoleUser {

    private List<REL_RoleUserDTO> resultSetWhileLoop(ResultSet resultset) throws SQLException {
        REL_RoleUserDTO userRole = null;
        List<REL_RoleUserDTO> userRoleList = new ArrayList<>();

        try{
            while (resultset.next()) {
                userRole = new REL_RoleUserDTO(resultset.getInt(1), resultset.getInt(2));
                userRoleList.add(userRole);
            }
        }
        catch(SQLException ex){
            throw new SQLException(ex);
        }
        return userRoleList;
    }

    private PreparedStatement setCreatePreparedStatement(PreparedStatement pStmt, REL_RoleUserDTO userRole) throws SQLException {
        try{
            pStmt.setInt(1, userRole.getUserId());
            pStmt.setInt(2, userRole.getRoleId());
        } catch (SQLException ex){
            throw new SQLException(ex);
        }
        return pStmt;
    }

    private PreparedStatement setUpdatePreparedStatement(PreparedStatement pStmt, REL_RoleUserDTO userRole) throws SQLException {
        try{
            pStmt.setInt(1,userRole.getUserId());
            pStmt.setInt(2,userRole.getRoleId());
            pStmt.setInt(3,userRole.getUserId());
            pStmt.setInt(4,userRole.getRoleId());
        } catch (SQLException ex){
            throw new SQLException(ex);
        }
        return pStmt;
    }

    @Override
    public boolean assignUserRole(REL_RoleUserDTO singleUserRole) throws SQLException {
        try (Connection conn = static_createConnection()) {
            PreparedStatement pStmt = conn.prepareStatement("INSERT INTO rel_roles_users (user_id, role_id) VALUES (?,?)");

            setCreatePreparedStatement(pStmt, singleUserRole);

            pStmt.executeUpdate();
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }
        return doesUserHaveRole(singleUserRole.getUserId(),singleUserRole.getRoleId());
    }

    @Override
    public List<REL_RoleUserDTO> assignUserMultipleRoles(List<REL_RoleUserDTO> listOfUserRoles) throws SQLException {
        return null;
    }

    @Override
    public boolean doesUserHaveRole(int userId, int roleId) throws SQLException {
        return false;
    }


    @Override
    public List<REL_RoleUserDTO> readAllUserRoles() throws SQLException {
        return null;
    }

    @Override
    public REL_RoleUserDTO updateUsersSingleRole(REL_RoleUserDTO userRole) throws SQLException {
        return null;
    }

    @Override
    public List<REL_RoleUserDTO> updateUsersMultipleRoles(List<REL_RoleUserDTO> listOfUserRoles) throws SQLException {
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
