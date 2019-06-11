package DataAccess.dao;

import DataAccess.dto.UserDTO;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

public interface I_DAL_User extends Serializable {

    UserDTO createSingleUser( UserDTO singleUser ) throws SQLException;
    List<UserDTO> createMultipleUsers( List<UserDTO> listOfUsers) throws SQLException;

    UserDTO readSingleUserbyId(int UserId ) throws SQLException;
    List<UserDTO> readMultipleUsersByList (List<Integer> listOfUserIds) throws SQLException;
    List<UserDTO> readUserbySearch(String keyword) throws SQLException;
    List<UserDTO> readAllUsers() throws SQLException;

    UserDTO updateSingleUser(UserDTO User) throws SQLException;
    List<UserDTO> updateMultipleUsers(List<UserDTO> listOfUsers) throws SQLException;

    UserDTO deleteSingleUser(UserDTO User) throws SQLException;
    List<UserDTO> deleteMultipleUsers(List<UserDTO> listOfUsers) throws SQLException;
}