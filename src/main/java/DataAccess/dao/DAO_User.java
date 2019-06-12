package DataAccess.dao;

import DataAccess.dto.UserDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static DataAccess.dao.Connector.*;

public class DAO_User implements I_DAL_User {

    @Override
    public UserDTO createSingleUser(UserDTO singleUser) throws SQLException {
        return null;
    }

    @Override
    public List<UserDTO> createMultipleUsers(List<UserDTO> listOfUsers) throws SQLException {
        return null;
    }

    @Override
    public UserDTO readSingleUserbyId(int UserId) throws SQLException {
        return null;
    }

    @Override
    public List<UserDTO> readMultipleUsersByList(List<Integer> listOfUserIds) throws SQLException {
        return null;
    }

    @Override
    public List<UserDTO> readUserbySearch(String keyword) throws SQLException {
        return null;
    }

    @Override
    public List<UserDTO> readAllUsers() throws SQLException {
        return null;
    }

    @Override
    public UserDTO updateSingleUser(UserDTO user) throws SQLException {
        return null;
    }

    @Override
    public List<UserDTO> updateMultipleUsers(List<UserDTO> listOfUsers) throws SQLException {
        return null;
    }
}
