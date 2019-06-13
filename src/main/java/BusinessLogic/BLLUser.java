package main.java.BusinessLogic;

import main.java.Core.RoleDTO;
import main.java.Core.UserDTO;
import main.java.DataAccess.dao.DAO_User;
import main.java.DataAccess.dao.I_DAL_User;

import java.sql.SQLException;
import java.util.List;

public class BLLUser implements I_BLLUser {
    private I_DAL_User DAL_user = new DAO_User();

    @Override
    public UserDTO createUser(UserDTO singleUser, RoleDTO singleRole) throws SQLException {
        return null;
    }

    @Override
    public List<UserDTO> createUsers(List<UserDTO> listOfUsers, List<RoleDTO> listOfRoles) throws SQLException {
        return null;
    }

    @Override
    public UserDTO getUserById(int userId, boolean returnProductBatches, boolean returnAmounts) throws SQLException {
        return null;
    }

    @Override
    public List<UserDTO> getUsersByListOfIds(List<Integer> listOfUserIds) throws SQLException {
        return null;
    }

    @Override
    public List<UserDTO> getUserBySearch(String keyword) throws SQLException {
        return null;
    }

    @Override
    public List<UserDTO> getAllUsers() throws SQLException {
        return null;
    }

    @Override
    public List<UserDTO> getAllUsers(boolean roles, boolean admins, boolean labTech, boolean pharmacist, boolean prodLeader) throws SQLException {
        return null;
    }

    @Override
    public UserDTO updateUser(UserDTO user) throws SQLException {
        return null;
    }

    @Override
    public List<UserDTO> updateUsers(List<UserDTO> listOfUsers) throws SQLException {
        return null;
    }

    @Override
    public void deleteUser(int userId) throws SQLException {

    }

    @Override
    public void deleteUsers(List<Integer> userIds) throws SQLException {

    }
}
