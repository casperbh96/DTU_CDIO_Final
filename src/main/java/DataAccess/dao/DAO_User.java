package DataAccess.dao;

import DataAccess.dto.UserDTO;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

import static DataAccess.dao.Connector.static_commitTransAction;
import static DataAccess.dao.Connector.static_createConnection;
import static DataAccess.dao.Connector.static_startTransAction;
import static DataAccess.dao.Connector.*;

public class DAO_User implements main.java.dal.I_DAL_User {

    @Override
    public UserDTO createSingleUser(UserDTO singleUser) {
        return null;
    }

    @Override
    public List<UserDTO> createMultipleUsers(List<UserDTO> listOfUsers) {
        return null;
    }

    @Override
    public UserDTO readSingleUserbyId(int UserId) {
        return null;
    }

    @Override
    public List<UserDTO> readMultipleUsersByList(List<Integer> listOfUserIds) {
        return null;
    }

    @Override
    public List<UserDTO> readUserbySearch(String keyword) {
        return null;
    }

    @Override
    public List<UserDTO> readAllUsers() {
        return null;
    }

    @Override
    public UserDTO updateSingleUser(UserDTO User) {
        return null;
    }

    @Override
    public List<UserDTO> updateMultipleUsers(List<UserDTO> listOfUsers) {
        return null;
    }

    @Override
    public UserDTO deleteSingleUser(UserDTO User) {
        return null;
    }

    @Override
    public List<UserDTO> deleteMultipleUsers(List<UserDTO> listOfUsers) {
        return null;
    }
}
