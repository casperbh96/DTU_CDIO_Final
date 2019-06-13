package main.java.DataAccess.dao;

import main.java.Core.ResourceDTO;
import main.java.Core.RoleDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static main.java.DataAccess.dao.Connector.*;

public class DAO_Role implements I_DAL_Role {

    private List<RoleDTO> resultSetWhileLoop(ResultSet resultset) throws SQLException {
        RoleDTO role = null;
        List<RoleDTO> roleList = new ArrayList<>();

        try{
            while (resultset.next()) {
                role = new RoleDTO(resultset.getInt(1), resultset.getString(2));
                roleList.add(role);
            }
        }
        catch(SQLException ex){
            throw new SQLException(ex);
        }
        return roleList;
    }

    private PreparedStatement setCreatePreparedStatement(PreparedStatement pStmt, RoleDTO role) throws SQLException {
        try{
            pStmt.setInt(1, role.getRoleId());
            pStmt.setString(2, role.getRolename());
        } catch (SQLException ex){
            throw new SQLException(ex);
        }
        return pStmt;
    }

    private PreparedStatement setUpdatePreparedStatement(PreparedStatement pStmt, RoleDTO role) throws SQLException {
        try{
            pStmt.setString(1, role.getRolename());
            pStmt.setInt(2, role.getRoleId());
        } catch (SQLException ex){
            throw new SQLException(ex);
        }
        return pStmt;
    }

    @Override
    public RoleDTO createSingleRole(RoleDTO singleRole) throws SQLException {
        try (Connection conn = static_createConnection()) {
            PreparedStatement pStmt = conn.prepareStatement("INSERT INTO roles (role_id, rolename) VALUES (?,?)");

            setCreatePreparedStatement(pStmt, singleRole);

            pStmt.executeUpdate();
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }
        return readSingleRoleById(singleRole.getRoleId());
    }

    @Override
    public List<RoleDTO> createMultipleRoles(List<RoleDTO> listOfRoles) throws SQLException {
        List<Integer> idList = new ArrayList<>();

        try (Connection conn = static_createConnection()) {
            static_startTransAction(conn);
            PreparedStatement pStmt = conn.prepareStatement("INSERT INTO roles (role_id, rolename) VALUES (?,?)");

            for (RoleDTO role : listOfRoles) {
                idList.add(role.getRoleId());

                setCreatePreparedStatement(pStmt, role);

                pStmt.addBatch();
            }
            pStmt.executeBatch();
            static_commitTransAction(conn);
        } catch (BatchUpdateException batchEx) {
            throw new BatchUpdateException(batchEx);
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }

        return readMultipleRolesByList(idList);
    }

    @Override
    public RoleDTO readSingleRoleById(int roleId) throws SQLException {
        RoleDTO role = null;

        try (Connection conn = static_createConnection()) {
            PreparedStatement pStmt = conn.prepareStatement("SELECT * FROM roles WHERE role_id = ?");

            pStmt.setInt(1, roleId);
            ResultSet resultset = pStmt.executeQuery();

            // Move pointer to first row before Id, then to row with Id (fix)
            resultset.beforeFirst();
            resultset.next();

            role = new RoleDTO(resultset.getInt(1), resultset.getString(2));
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }
        return role;
    }

    @Override
    public List<RoleDTO> readMultipleRolesByList(List<Integer> listOfRoleIds) throws SQLException {
        List<RoleDTO> roleList = new ArrayList<>();

        String parameters = static_parameterBuilder(listOfRoleIds);

        try (Connection conn = static_createConnection()) {
            PreparedStatement pStmt = conn.prepareStatement("SELECT * FROM roles WHERE role_id IN (" + parameters + ")");

            static_setIntPreparedStatements(pStmt,listOfRoleIds);

            ResultSet resultset = pStmt.executeQuery();

            roleList = resultSetWhileLoop(resultset);
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }
        return roleList;
    }

    @Override
    public List<RoleDTO> readRoleBySearch(String keyword) throws SQLException {
        List<RoleDTO> roleList = new ArrayList<>();

        try (Connection conn = static_createConnection()) {
            PreparedStatement pStmt = conn.prepareStatement("select * from roles " +
                    "WHERE role_id LIKE ? OR rolename LIKE ?");
            pStmt.setString(1, "%" + keyword + "%");
            pStmt.setString(2, "%" + keyword + "%");

            ResultSet resultset = pStmt.executeQuery();

            roleList = resultSetWhileLoop(resultset);
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }
        return roleList;
    }

    @Override
    public List<RoleDTO> readAllRoles() throws SQLException {
        List<RoleDTO> roleList = new ArrayList<>();

        try (Connection connection = static_createConnection()) {
            PreparedStatement pStmt = connection.prepareStatement("SELECT * FROM roles WHERE role_id");
            ResultSet resultset = pStmt.executeQuery();

            roleList = resultSetWhileLoop(resultset);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roleList;
    }

    @Override
    public RoleDTO updateSingleRole(RoleDTO role) throws SQLException {
        try (Connection conn = static_createConnection()) {
            PreparedStatement pStmt = conn.prepareStatement("UPDATE roles SET rolename = ? WHERE role_id = ?");

            pStmt = setUpdatePreparedStatement(pStmt, role);

            pStmt.executeUpdate();
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }
        return readSingleRoleById(role.getRoleId());
    }

    @Override
    public List<RoleDTO> updateMultipleRoles(List<RoleDTO> listOfRoles) throws SQLException {
        List<Integer> idList = new ArrayList<>();

        try (Connection conn = static_createConnection()) {
            static_startTransAction(conn);
            PreparedStatement pStmt = conn.prepareStatement("UPDATE roles SET rolename = ? WHERE role_id = ?");

            for (RoleDTO role : listOfRoles) {
                idList.add(role.getRoleId());

                setUpdatePreparedStatement(pStmt, role);
                pStmt.addBatch();
            }

            pStmt.executeBatch();
            static_commitTransAction(conn);

        } catch (BatchUpdateException batchEx){
            throw new BatchUpdateException(batchEx);
        }
        return readMultipleRolesByList(idList);
    }
}
