package main.java.BusinessLogic;

import main.java.Core.UserDTO;

import java.sql.SQLException;
import java.util.List;

public interface I_User {
    UserDTO createUser(UserDTO singleUser) throws SQLException;
    List<UserDTO> createUsers( List<UserDTO> listOfUsers) throws SQLException;

    UserDTO getSingleUserbyId(int userId ) throws SQLException;
    List<UserDTO> getMultipleUsersByList (List<Integer> listOfUserIds) throws SQLException;
    List<UserDTO> getUserbySearch(String keyword) throws SQLException;
    List<UserDTO> getAllUsers() throws SQLException;

    UserDTO updateUser(UserDTO user) throws SQLException;
    List<UserDTO> updateUsers(List<UserDTO> listOfUsers) throws SQLException;
}
