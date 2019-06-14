package main.java.BusinessLogic;

import main.java.Core.REL_RoleUserDTO;
import main.java.Core.RoleDTO;
import main.java.Core.UserDTO;
import main.java.DataAccess.dao.*;

import javax.management.relation.Role;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BLLUser implements I_BLLUser {
    private I_DAL_User DAL_user = new DAO_User();
    private I_DAL_Role DAL_role = new DAO_Role();
    private I_DAL_REL_RoleUser DAL_roleUser = new DAO_REL_RoleUser();

    @Override
    public UserDTO createUser(UserDTO singleUser, int roleId) throws SQLException {
        UserDTO user = DAL_user.createSingleUser(singleUser);

        if(roleId != 0){
            REL_RoleUserDTO newRoleUser = new REL_RoleUserDTO(singleUser.getUserId(), roleId);
            boolean addedRole = DAL_roleUser.assignUserRole(newRoleUser);

            if(!addedRole){
                return null;
            }
        }

        return user;
    }

    @Override
    public UserDTO createUser(UserDTO singleUser, List<Integer> listOfRoles) throws SQLException {
        UserDTO user = DAL_user.createSingleUser(singleUser);
        boolean shouldReturnNull = false;

        if(listOfRoles != null){
            for(int i : listOfRoles){
                REL_RoleUserDTO newRoleUser = new REL_RoleUserDTO(singleUser.getUserId(), i);
                boolean addedRole = DAL_roleUser.assignUserRole(newRoleUser);

                if(!addedRole){
                    shouldReturnNull = true;
                }
            }
        }

        if(shouldReturnNull) return null;

        return user;
    }

    @Override
    public List<UserDTO> createUsers(List<UserDTO> listOfUsers, List<Integer> listOfRoleIds) throws SQLException {
        List<UserDTO> userList = DAL_user.createMultipleUsers(listOfUsers);

        if(listOfRoleIds != null){
            List<REL_RoleUserDTO> roleUserList = new ArrayList<>(Arrays.asList(new REL_RoleUserDTO[userList.size()]));
            for(int i = 0; i < listOfUsers.size(); i++){
                roleUserList.get(i).setUserId(userList.get(i).getUserId());
                roleUserList.get(i).setRoleId(listOfRoleIds.get(i));
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

    // Booleans because we might return more than one role
    @Override
    public Object[] getAllUsers(boolean roles, boolean admins, boolean labTech, boolean pharmacist, boolean prodLeader) throws SQLException {
        List<UserDTO> allUsers = getAllUsers();
        List<REL_RoleUserDTO> allUserRoles = new ArrayList<>();
        List<UserDTO> returnListWithUsers = new ArrayList<>();
        List<ArrayList<REL_RoleUserDTO>> returnListWithRole = new ArrayList<>();

        int adminRoleId = 0;
        int labTechRoleId = 0;
        int pharmacistRoleId = 0;
        int prodLeaderRoleId = 0;

        if(roles) {
            allUserRoles = DAL_roleUser.readAllUserRoles();
            List<RoleDTO> allRoles = DAL_role.readAllRoles();

            for (RoleDTO r : allRoles) {
                if (r.getRolename().toLowerCase().equals(       "admin"                )) adminRoleId        = r.getRoleId();
                if (r.getRolename().toLowerCase().equals(       "laborant technician"  )) labTechRoleId      = r.getRoleId();
                if (r.getRolename().toLowerCase().equals(       "pharmacist"           )) pharmacistRoleId   = r.getRoleId();
                if (r.getRolename().toLowerCase().equals(       "productionleader"     )) prodLeaderRoleId   = r.getRoleId();
            }
        }

        int index = 0;
        for(UserDTO user : allUsers){
            if(roles){
                for(REL_RoleUserDTO role : allUserRoles){
                    if(!admins && !labTech && !pharmacist && !prodLeader) return new Object[]{allUsers, allUserRoles};

                    if(user.getUserId() == role.getUserId()){
                        if(admins && role.getRoleId() == adminRoleId){
                            returnListWithUsers.add(user);
                            returnListWithRole.get(index).add(role);
                        }

                        if(labTech && role.getRoleId() == labTechRoleId){
                            returnListWithUsers.add(user);
                            returnListWithRole.get(index).add(role);
                        }

                        if(pharmacist && role.getRoleId() == pharmacistRoleId){
                            returnListWithUsers.add(user);
                            returnListWithRole.get(index).add(role);
                        }

                        if(prodLeader && role.getRoleId() == prodLeaderRoleId){
                            returnListWithUsers.add(user);
                            returnListWithRole.get(index).add(role);
                        }
                    }
                }
            } else {
                return new Object[]{allUsers, null};
            }
            index++;
        }

        return new Object[]{returnListWithUsers, allUserRoles};
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
