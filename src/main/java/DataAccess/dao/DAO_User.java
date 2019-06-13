package main.java.DataAccess.dao;

import main.java.Core.UserDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static main.java.DataAccess.dao.Connector.*;

public class DAO_User implements I_DAL_User {
    private List<UserDTO> resultSetWhileLoop(ResultSet resultset) throws SQLException {
        UserDTO user = null;
        List<UserDTO> userList = new ArrayList<>();

        try{
            while (resultset.next()) {
                user = new UserDTO(resultset.getInt(1), resultset.getString(2), resultset.getString(3), resultset.getBoolean(4));
                userList.add(user);
            }
        }
        catch(SQLException ex){
            throw new SQLException(ex);
        }

        return userList;
    }

    private PreparedStatement setCreatePreparedStatement(PreparedStatement pStmt, UserDTO user) throws SQLException {
        try{
            pStmt.setInt(1, user.getUserId());
            pStmt.setString(2, user.getUsername());
            pStmt.setString(3, user.getInitials());
            pStmt.setBoolean(4, user.isInactive());
        } catch (SQLException ex){
            throw new SQLException(ex);
        }
        return pStmt;
    }

    private PreparedStatement setUpdatePreparedStatement(PreparedStatement pStmt, UserDTO user) throws SQLException {
        try{
            pStmt.setString(1, user.getUsername());
            pStmt.setString(2, user.getInitials());
            pStmt.setBoolean(3, user.isInactive());
            pStmt.setInt(4, user.getUserId());
        } catch (SQLException ex){
            throw new SQLException(ex);
        }
        return pStmt;
    }

    @Override
    public UserDTO createSingleUser(UserDTO singleUser) throws SQLException {
        try (Connection conn = static_createConnection()) {
            PreparedStatement pStmt = conn.prepareStatement("INSERT INTO users (user_id, username, initials, inactive) VALUES (?,?,?,?)");

            pStmt = setCreatePreparedStatement(pStmt, singleUser);

            pStmt.executeUpdate();
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }
        return readSingleUserById(singleUser.getUserId());
    }

    @Override
    public List<UserDTO> createMultipleUsers(List<UserDTO> listOfUsers) throws SQLException {
        List<Integer> idList = new ArrayList<>();

        try (Connection conn = static_createConnection()) {
            static_startTransAction(conn);
            PreparedStatement pStmt = conn.prepareStatement("INSERT INTO users (user_id, username, initials, inactive) VALUES (?,?,?,?)");

            int index = 0;
            for (UserDTO user : listOfUsers) {
                idList.add(user.getUserId());

                pStmt = setCreatePreparedStatement(pStmt, user);

                pStmt.addBatch();
                index++;
            }
            pStmt.executeBatch();
            static_commitTransAction(conn);
        } catch (BatchUpdateException batchEx) {
            throw new BatchUpdateException(batchEx);
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }

        return readMultipleUsersByList(idList);
    }

    @Override
    public UserDTO readSingleUserById(int userId) throws SQLException {
        UserDTO user = null;

        try (Connection conn = static_createConnection()) {
            PreparedStatement pStmt = conn.prepareStatement("SELECT * FROM users WHERE user_id=?");

            pStmt.setInt(1, userId);
            ResultSet resultset = pStmt.executeQuery();

            // Move pointer to first row before Id, then to row with Id (fix)
            resultset.beforeFirst();
            resultset.next();

            user = new UserDTO(resultset.getInt(1), resultset.getString(2), resultset.getString(3), resultset.getBoolean(4));
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }
        return user;
    }

    @Override
    public List<UserDTO> readMultipleUsersByList(List<Integer> listOfUserIds) throws SQLException {
        List<UserDTO> userList = new ArrayList<>();
        StringBuilder builder = new StringBuilder();

        // Produce string with number of ? equal to size of listOfResourceIds
        for (int i = 0; i < listOfUserIds.size(); i++) {
            if (i == listOfUserIds.size() - 1) {
                builder.append("?");
            } else {
                builder.append("?,");
            }
        }

        try (Connection conn = static_createConnection()) {
            // Turns into SELECT * FROM resources WHERE resource_id IN () with the string builder in the parenthesis
            PreparedStatement pStmt = conn.prepareStatement("SELECT * FROM users WHERE user_id IN (" + builder.toString() + ")");

            // Set each of the ? to the corresponding Id from listOfResourceIds
            int index = 1;
            for (int i : listOfUserIds) {
                pStmt.setInt(index++, i);
            }

            ResultSet resultset = pStmt.executeQuery();

            userList = resultSetWhileLoop(resultset);

        } catch (SQLException ex) {
            throw new SQLException(ex);
        }

        return userList;
    }

    @Override
    public List<UserDTO> readUserBySearch(String keyword) throws SQLException {
        List<UserDTO> userList = new ArrayList<>();

        try (Connection conn = static_createConnection()) {
            PreparedStatement pStmt = conn.prepareStatement("select * from users " +
                    "WHERE user_id LIKE ? OR username LIKE ? OR initials LIKE ? OR inactive LIKE ?");
            pStmt.setString(1, "%" + keyword + "%");
            pStmt.setString(2, "%" + keyword + "%");
            pStmt.setString(3, "%" + keyword + "%");
            pStmt.setString(4, "%" + keyword + "%");
            ResultSet resultset = pStmt.executeQuery();

            userList = resultSetWhileLoop(resultset);

        } catch (SQLException ex) {
            throw new SQLException(ex);
        }

        return userList;
    }

    @Override
    public List<UserDTO> readAllUsers() throws SQLException {
        List<UserDTO> userList = new ArrayList<>();

        try (Connection connection = static_createConnection()) {
            PreparedStatement pStmt = connection.prepareStatement("SELECT * FROM users WHERE user_id");
            ResultSet resultset = pStmt.executeQuery();

            userList = resultSetWhileLoop(resultset);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public UserDTO updateSingleUser(UserDTO user) throws SQLException {
        try (Connection conn = static_createConnection()) {
            PreparedStatement pStmt = conn.prepareStatement("UPDATE users SET username = ?, initials = ?, inactive = ? WHERE user_id = ?");

            pStmt = setUpdatePreparedStatement(pStmt, user);

            pStmt.executeUpdate();

        } catch (SQLException ex) {
            throw new SQLException(ex);
        }

        return readSingleUserById(user.getUserId());
    }

    @Override
    public List<UserDTO> updateMultipleUsers(List<UserDTO> listOfUsers) throws SQLException {
        List<Integer> idList = new ArrayList<>();

        try (Connection conn = static_createConnection()) {
            static_startTransAction(conn);
            PreparedStatement pStmt = conn.prepareStatement("UPDATE users SET username = ?, initials = ?, inactive = ? WHERE user_id = ?");

            int index = 0;
            for (UserDTO user : listOfUsers) {
                idList.add(user.getUserId());

                pStmt = setUpdatePreparedStatement(pStmt, user);

                pStmt.addBatch();
                index++;
            }

            pStmt.executeBatch();
            static_commitTransAction(conn);

        } catch (BatchUpdateException batchEx){
            throw new BatchUpdateException(batchEx);
        }
        catch (SQLException ex) {
            throw new SQLException(ex);
        }

        return readMultipleUsersByList(idList);
    }

    @Override
    public UserDTO setInactiveSingleUser(int userId) throws SQLException {
        return null;
    }

    @Override
    public UserDTO setInactiveMultipleUsers(List<Integer> listOfUserIds) throws SQLException {
        return null;
    }
}
