package DataAccess.dao;

import DataAccess.dto.UserDTO;
import com.mysql.cj.protocol.Resultset;
import sun.awt.image.ImageWatched;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static DataAccess.dao.Connector.static_commitTransAction;
import static DataAccess.dao.Connector.static_createConnection;
import static DataAccess.dao.Connector.static_startTransAction;
import static DataAccess.dao.Connector.*;

public class DAO_User implements I_DAL_User {

    @Override
    public UserDTO createSingleUser(UserDTO singleUser) {
        try(Connection connection = static_createConnection()) {

            PreparedStatement pStmt = connection.prepareStatement("INSERT INTO users (user_id, username, initials) VALUES (?,?,?)");

            pStmt.setInt(1, singleUser.getUserId());
            pStmt.setString(2, singleUser.getUsername());
            pStmt.setString(3, singleUser.getInitials());

            //TODO husk at sætte inactive til default 0 / false i sql

            pStmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return readSingleUserbyId(singleUser.getUserId());          //TODO virker dette???
    }

    @Override
    public List<UserDTO> createMultipleUsers(List<UserDTO> listOfUsers) {
        try(Connection connection = static_createConnection()) {

            for (int i = 0; i < listOfUsers.size(); i++) {
                PreparedStatement pStmt = connection.prepareStatement("INSERT INTO users (user_id, username, initials) VALUES (?,?,?)");

                pStmt.setInt(1, listOfUsers.get(i).getUserId());
                pStmt.setString(2, listOfUsers.get(i).getUsername());
                pStmt.setString(3, listOfUsers.get(i).getInitials());

                pStmt.executeUpdate();              //TODO kan dette gøres uden at skulle forbinde til databasen hver iteration?
            }

            //TODO husk at sætte inactive til default 0 i sql

//            pStmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        List<Integer> ids = new LinkedList<>();
        for (int i = 0; i < listOfUsers.size(); i++) {
            ids.add(listOfUsers.get(i).getUserId());
        }
        return readMultipleUsersByList(ids);          //TODO virker dette???
    }

    @Override
    public UserDTO readSingleUserbyId(int userId) {

        UserDTO user = null;

        try(Connection connection = static_createConnection()) {

            PreparedStatement pStmt = connection.prepareStatement("SELECT * FROM users WHERE user_id = ?");

            pStmt.setInt(1, userId);

            ResultSet resultset = pStmt.executeQuery();

            user = new UserDTO(resultset.getInt(1),resultset.getString(2),resultset.getString(3),resultset.getBoolean(4));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public List<UserDTO> readMultipleUsersByList(List<Integer> listOfUserIds) {

        List<UserDTO> users = new LinkedList<>();
        UserDTO user = null;

        try(Connection connection = static_createConnection()) {

            for (int i = 0; i < listOfUserIds.size(); i++) {
                PreparedStatement pStmt = connection.prepareStatement("SELECT * FROM users WHERE user_id = ?");

                pStmt.setInt(1, listOfUserIds.get(i));

                ResultSet resultset = pStmt.executeQuery();

                user = new UserDTO(resultset.getInt(1), resultset.getString(2), resultset.getString(3), resultset.getBoolean(4));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;           //if list is empty method failed to execute properly
    }

    @Override
    public List<UserDTO> readUserbySearch(String keyword) throws SQLException {
        UserDTO res;
        List<UserDTO> resourceList = new ArrayList<>();

        try (Connection conn = static_createConnection()) {
            PreparedStatement pStmt = conn.prepareStatement("select * from resources " +
                    "WHERE resource_id LIKE ? OR resource_name LIKE ? OR reorder LIKE ?");      //TODO ret så det passer til UserDAO
            pStmt.setString(1, "%" + keyword + "%");
            pStmt.setString(2, "%" + keyword + "%");
            pStmt.setString(3, "%" + keyword + "%");
            ResultSet resultSet = pStmt.executeQuery();

            while (resultSet.next()) {
                res = new UserDTO(resultSet.getInt("resource_id"), resultSet.getString("resource_name"), resultSet.getInt("reorder"), amount);
                resourceList.add(res);
            }
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }

        return resourceList;
    }

    @Override
    public List<UserDTO> readAllUsers() {
        List<UserDTO> users = new LinkedList<>();
        UserDTO user = null;

        try(Connection connection = static_createConnection()) {

            PreparedStatement pStmt = connection.prepareStatement("SELECT * FROM users WHERE user_id");

            ResultSet resultset = pStmt.executeQuery();

            while(resultset.next()) {
                user = new UserDTO(resultset.getInt(1), resultset.getString(2), resultset.getString(3), resultset.getBoolean(4));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;           //if list is empty method failed to execute properly
    }

    @Override
    public UserDTO updateSingleUser(UserDTO user) {
        try(Connection connection = static_createConnection()){
            // try with resources
            PreparedStatement pStmt = connection.prepareStatement("UPDATE users SET user_id = ?, username = ?, initials = ? WHERE user_id = ?");

            pStmt.setInt(1, user.getUserId());          //TODO this ok?? check if probably should be added
            pStmt.setString(2 ,user.getUsername());
            pStmt.setString(3, user.getInitials());
            pStmt.setInt(4, user.getUserId());

            pStmt.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();
        }
        return readSingleUserbyId(user.getUserId());        //TODO this ok??
    }

    @Override
    public List<UserDTO> updateMultipleUsers(List<UserDTO> listOfUsers) {

        List<Integer> ids = new LinkedList<>();
        try(Connection connection = static_createConnection()) {

            for (int i = 0; i < listOfUsers.size(); i++) {
                PreparedStatement pStmt = connection.prepareStatement("UPDATE users SET user_id = ?, username = ?, initials = ? WHERE user_id = ?");

                pStmt.setInt(1, listOfUsers.get(i).getUserId());
                pStmt.setString(2, listOfUsers.get(i).getUsername());
                pStmt.setString(3, listOfUsers.get(i).getInitials());
                pStmt.setInt(4, listOfUsers.get(i).getUserId());
                                                    //TODO skal 'inactive' også kunne ændres??

                pStmt.executeUpdate();              //TODO kan dette gøres uden at skulle forbinde til databasen hver iteration?

                ResultSet resultset = pStmt.executeQuery();

                while (resultset.next()){
                    ids.add(resultset.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return readMultipleUsersByList(ids);          //TODO virker dette???
    }

    @Override
    public UserDTO deleteSingleUser(UserDTO user) {
        try(Connection connection = static_createConnection()){

            PreparedStatement pStmt = connection.prepareStatement("UPDATE users SET inactive = ? WHERE user_id = ?");

            pStmt.setBoolean(1, true);
            pStmt.setInt(2, user.getUserId());
            pStmt.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
        return readSingleUserbyId(user.getUserId()); //TODO her kan vel ikke rigtigt returneres noget??
    }

    @Override
    public List<UserDTO> deleteMultipleUsers(List<UserDTO> listOfUsers) {

        List<Integer> ids = new LinkedList<>();
        try(Connection connection = static_createConnection()){

            for (int i = 0; i < listOfUsers.size(); i++) {

                PreparedStatement pStmt = connection.prepareStatement("UPDATE users SET inactive = ? WHERE user_id = ?");
                pStmt.setBoolean(1, true);
                pStmt.setInt(2, listOfUsers.get(i).getUserId());
                pStmt.executeUpdate();

                ResultSet resultset = pStmt.executeQuery();

                while (resultset.next()){
                    ids.add(resultset.getInt(1));
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return readMultipleUsersByList(ids); //TODO her kan vel ikke rigtigt returneres noget?? Nu returneres en liste, hvor alle skal være inactive = true
    }
}
