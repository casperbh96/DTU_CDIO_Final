package main.java.DataAccess.arrList;

import main.java.Core.UserDTO;
import main.java.DataAccess.dao.I_DAL_User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ARR_User implements I_DAL_User {
    List<UserDTO> users;

    public ARR_User(){
        users = new ArrayList<>();
        users.add(new UserDTO(1, "Casper", "CA", false));
        users.add(new UserDTO(2, "Hans", "HA", false));
        users.add(new UserDTO(3, "Emil", "EM", false));
        users.add(new UserDTO(4, "Mette", "ME", false));
    }

    @Override
    public UserDTO createSingleUser(UserDTO singleUser) throws SQLException {
        users.add(singleUser);
        return readSingleUserbyId(singleUser.getUserId());
    }

    @Override
    public List<UserDTO> createMultipleUsers(List<UserDTO> listOfUsers) throws SQLException {
        List<Integer> listOfIds = new ArrayList<>();
        for(UserDTO user : listOfUsers){
            users.add(user);
            listOfIds.add(user.getUserId());
        }
        return readMultipleUsersByList(listOfIds);
    }

    @Override
    public UserDTO readSingleUserbyId(int userId) {
        for(UserDTO user : users){
            if(user.getUserId() == userId){
                return user;
            }
        }
        return null;
    }

    @Override
    public List<UserDTO> readMultipleUsersByList(List<Integer> listOfUserIds) {
        List<UserDTO> userList = new ArrayList<>();
        for(UserDTO user : users){
            for(Integer i : listOfUserIds){
                if(user.getUserId() == i){
                    userList.add(user);
                }
            }
        }
        return userList;
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
    public UserDTO updateSingleUser(UserDTO user) {
        return null;
    }

    @Override
    public List<UserDTO> updateMultipleUsers(List<UserDTO> listOfUsers) {
        return null;
    }
}
