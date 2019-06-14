package main.java.BusinessLogic;

import main.java.Core.REL_RoleUserDTO;
import main.java.Core.RoleDTO;
import main.java.Core.UserDTO;
import main.java.DataAccess.dao.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BLLUser implements I_BLLUser {
    private I_DAL_User DAL_user = new DAO_User();
    private I_BLLRole BLL_role = new BLLRole();
    private I_DAL_REL_RoleUser DAL_roleUser = new DAO_REL_RoleUser();

    @Override
    public UserDTO createUser(UserDTO singleUser, RoleDTO singleRole) throws SQLException {
        UserDTO user = DAL_user.createSingleUser(singleUser);
        if(singleRole != null){
            RoleDTO role = BLL_role.createRole(singleRole);
            REL_RoleUserDTO newRoleUser = new REL_RoleUserDTO(singleUser.getUserId(), role.getRoleId());
            boolean addedRole = DAL_roleUser.assignUserRole(newRoleUser);

            if(!addedRole){
                return null;
            }
        }

        return user;
    }

    @Override
    public List<UserDTO> createUsers(List<UserDTO> listOfUsers, List<RoleDTO> listOfRoles) throws SQLException {
        List<UserDTO> userList = DAL_user.createMultipleUsers(listOfUsers);

        if(listOfRoles != null){
            List<RoleDTO> roleList = BLL_role.createRoles(listOfRoles);

            List<REL_RoleUserDTO> roleUserList = new ArrayList<>(Arrays.asList(new REL_RoleUserDTO[userList.size()]));
            for(int i = 0; i < listOfUsers.size(); i++){
                roleUserList.get(i).setUserId(userList.get(i).getUserId());
                roleUserList.get(i).setRoleId(roleList.get(i).getRoleId());
            }

            boolean addedRoles = DAL_roleUser.assignUserMultipleRoles(roleUserList);
            if(!addedRoles){
                return null;
            }
        }
        return userList;
    }

    @Override
    public UserDTO getUserById(int userId) throws SQLException {
        return DAL_user.readSingleUserById(userId);
    }

    @Override
    public List<UserDTO> getUsersByListOfIds(List<Integer> listOfUserIds) throws SQLException {
        return DAL_user.readMultipleUsersByList(listOfUserIds);
    }

    @Override
    public List<UserDTO> getUserBySearch(String keyword) throws SQLException {
        return DAL_user.readUserBySearch(keyword);
    }

    @Override
    public List<UserDTO> getAllUsers() throws SQLException {
        return DAL_user.readAllUsers();
    }

    @Override
    public List<UserDTO> getAllUsers(boolean roles, boolean admins, boolean labTech, boolean pharmacist, boolean prodLeader) {
        // Booleans because we might return more than one role
        return null;
    }

    @Override
    public UserDTO updateUser(UserDTO user) throws SQLException {
        return DAL_user.updateSingleUser(user);
    }

    @Override
    public List<UserDTO> updateUsers(List<UserDTO> listOfUsers) throws SQLException {
        return DAL_user.updateMultipleUsers(listOfUsers);
    }

    @Override
    public void deleteUser(int userId) {

    }

    @Override
    public void deleteUsers(List<Integer> userIds) {

    }
}
