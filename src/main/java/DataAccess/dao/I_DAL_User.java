package main.java.DataAccess.dao;

import main.java.Core.UserDTO;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

public interface I_DAL_User extends Serializable {

    UserDTO createSingleUser(UserDTO singleUser ) throws SQLException;
    List<UserDTO> createMultipleUsers( List<UserDTO> listOfUsers ) throws SQLException;

    UserDTO readSingleUserById(int userId) throws SQLException;
    List<UserDTO> readMultipleUsersByList (List<Integer> listOfUserIds) throws SQLException;
    List<UserDTO> readUserBySearch(String keyword) throws SQLException;
    List<UserDTO> readAllUsers() throws SQLException;

    UserDTO updateSingleUser(UserDTO user) throws SQLException;
    List<UserDTO> updateMultipleUsers(List<UserDTO> listOfUsers) throws SQLException;

    UserDTO setInactiveSingleUser(int userId) throws SQLException;
    UserDTO setInactiveMultipleUsers(List<Integer> listOfUserIds) throws SQLException;
}
