package main.java.DataAccess.dao;

import com.sun.org.apache.regexp.internal.RE;
import main.java.Core.REL_RoleUserDTO;
import main.java.Core.RoleDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static main.java.DataAccess.dao.Connector.*;

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

//    private PreparedStatement setUpdatePreparedStatement(PreparedStatement pStmt, REL_RoleUserDTO userRole) throws SQLException {
//        try{
//            pStmt.setInt(1,userRole.getUserId());
//            pStmt.setInt(2,userRole.getRoleId());
//            pStmt.setInt(3,userRole.getUserId());
//            pStmt.setInt(4,userRole.getRoleId());
//        } catch (SQLException ex){
//            throw new SQLException(ex);
//        }
//        return pStmt;
//    }

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
    public boolean assignUserMultipleRoles(List<REL_RoleUserDTO> listOfUserRoles) throws SQLException {
        try (Connection conn = static_createConnection()) {
            static_startTransAction(conn);
            PreparedStatement pStmt = conn.prepareStatement("INSERT INTO rel_roles_users (user_id, role_id) VALUES (?,?)");

            for (REL_RoleUserDTO userRole : listOfUserRoles) {

                setCreatePreparedStatement(pStmt, userRole);

                pStmt.addBatch();
            }
            pStmt.executeBatch();
            static_commitTransAction(conn);
        } catch (BatchUpdateException batchEx) {
            throw new BatchUpdateException(batchEx);
        }
        return readAllUserRoles().containsAll(listOfUserRoles);
    }

    @Override
    public boolean doesUserHaveRole(int userId, int roleId) throws SQLException {
        REL_RoleUserDTO userRole;

        try (Connection conn = static_createConnection()) {
            PreparedStatement pStmt = conn.prepareStatement("SELECT * FROM rel_roles_users WHERE user_id = ? AND role_id = ?");

            pStmt.setInt(1, userId);
            pStmt.setInt(2, roleId);
            ResultSet resultset = pStmt.executeQuery();

            // Move pointer to first row before Id, then to row with Id (fix)
            resultset.beforeFirst();
            resultset.next();

            userRole = new REL_RoleUserDTO(resultset.getInt(1), resultset.getInt(2));
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }
        return userRole.getUserId() == userId && userRole.getRoleId() == roleId;
    }

    @Override
    public List<REL_RoleUserDTO> readUsersRoles(int user_id) throws SQLException {
        List<REL_RoleUserDTO> userRoleList = new ArrayList<>();

        try (Connection conn = static_createConnection()) {
            PreparedStatement pStmt = conn.prepareStatement("SELECT * FROM rel_roles_users WHERE user_id = ?");

            ResultSet resultset = pStmt.executeQuery();

            userRoleList = resultSetWhileLoop(resultset);
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }
        return userRoleList;
    }


    @Override
    public List<REL_RoleUserDTO> readAllUserRoles() throws SQLException {
        List<REL_RoleUserDTO> userRoles = new ArrayList<>();

        try (Connection connection = static_createConnection()) {
            PreparedStatement pStmt = connection.prepareStatement("SELECT * FROM rel_roles_users");
            ResultSet resultset = pStmt.executeQuery();

            userRoles = resultSetWhileLoop(resultset);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userRoles;
    }


    @Override
    public void deleteUserRole(int userId, int roleId) throws SQLException {
        try(Connection connection = static_createConnection()){

            PreparedStatement pStmt = connection.prepareStatement("DELETE FROM rel_roles_users WHERE user_id = ? AND role_id = ?");

            pStmt.setInt(1, userId);
            pStmt.setInt(2, roleId);
            pStmt.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void deleteMultipleUserRole(List<REL_RoleUserDTO> userRoles) throws SQLException {
        try (Connection conn = static_createConnection()) {
            static_startTransAction(conn);
            PreparedStatement pStmt = conn.prepareStatement("DELETE FROM rel_roles_users WHERE user_id = ? AND role_id = ?");

            for (REL_RoleUserDTO userRole : userRoles) {

                pStmt.setInt(1, userRole.getUserId());
                pStmt.setInt(2, userRole.getRoleId());

                pStmt.addBatch();
            }
            pStmt.executeBatch();
            static_commitTransAction(conn);

        }  catch (SQLException ex) {
            throw new SQLException(ex);
        }
    }


}
