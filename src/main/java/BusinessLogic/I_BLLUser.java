package main.java.BusinessLogic;

import main.java.Core.RoleDTO;
import main.java.Core.UserDTO;

import java.sql.SQLException;
import java.util.List;

public interface I_BLLUser {
    UserDTO createUser(UserDTO singleUser, RoleDTO singleRole) throws SQLException;
    List<UserDTO> createUsers(List<UserDTO> listOfUsers, List<RoleDTO> listOfRoles) throws SQLException;

    UserDTO getUserById(int userId, boolean returnProductBatches, boolean returnAmounts) throws SQLException;
    List<UserDTO> getUsersByListOfIds (List<Integer> listOfUserIds) throws SQLException;
    List<UserDTO> getUserBySearch(String keyword) throws SQLException;
    List<UserDTO> getAllUsers() throws SQLException;
    List<UserDTO> getAllUsers(boolean roles, boolean admins, boolean labTech, boolean pharmacist, boolean prodLeader) throws SQLException;

    UserDTO updateUser(UserDTO user) throws SQLException;
    List<UserDTO> updateUsers(List<UserDTO> listOfUsers) throws SQLException;

    void deleteUser(int userId) throws SQLException;
    void deleteUsers(List<Integer> userIds) throws SQLException;
}
