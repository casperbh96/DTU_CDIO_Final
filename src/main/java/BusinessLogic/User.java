package main.java.BusinessLogic;

import main.java.Core.UserDTO;
import main.java.DataAccess.dao.DAO_User;
import main.java.DataAccess.dao.I_DAL_User;

import java.sql.SQLException;
import java.util.List;

public class User implements I_User{
    private I_DAL_User DAL_user;
    private UserDTO uDTO;

    public User(int userId, String username, String initials, boolean inactive){
        uDTO = new UserDTO(userId, username, initials, inactive);
    }

    @Override
    public UserDTO createUser(UserDTO singleUser) throws SQLException {
        return null;
    }

    @Override
    public List<UserDTO> createUsers(List<UserDTO> listOfUsers) throws SQLException {
        return null;
    }

    @Override
    public UserDTO getSingleUserbyId(int userId) throws SQLException {
        return null;
    }

    @Override
    public List<UserDTO> getMultipleUsersByList(List<Integer> listOfUserIds) throws SQLException {
        return null;
    }

    @Override
    public List<UserDTO> getUserbySearch(String keyword) throws SQLException {
        return null;
    }

    public List<UserDTO> getAllUsers() throws SQLException {
        try{
            return DAL_user.readAllUsers();
        }
        catch(SQLException ex){
            throw new SQLException(ex);
        }
    }

    @Override
    public UserDTO updateUser(UserDTO user) throws SQLException {
        return null;
    }

    @Override
    public List<UserDTO> updateUsers(List<UserDTO> listOfUsers) throws SQLException {
        return null;
    }
}
