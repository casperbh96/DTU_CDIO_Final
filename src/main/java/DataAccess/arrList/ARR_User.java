package main.java.DataAccess.arrList;

import main.java.Core.UserDTO;
import main.java.DataAccess.dao.I_DAL_User;

import java.lang.reflect.Field;
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
    public UserDTO createSingleUser(UserDTO singleUser) {
        users.add(singleUser);
        return readSingleUserById(singleUser.getUserId());
    }

    @Override
    public List<UserDTO> createMultipleUsers(List<UserDTO> listOfUsers) {
        List<Integer> listOfIds = new ArrayList<>();
        for(UserDTO user : listOfUsers){
            users.add(user);
            listOfIds.add(user.getUserId());
        }
        return readMultipleUsersByList(listOfIds);
    }

    @Override
    public UserDTO readSingleUserById(int userId) {
        UserDTO u = null;
        for(UserDTO user : users){
            if(user.getUserId() == userId){
                u = user;
            }
        }
        return u;
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
    public List<UserDTO> readUserBySearch(String keyword) {
        List<UserDTO> returnList = new ArrayList<>();

        for(UserDTO user : users){
            if(user.toString().contains(keyword)){
                returnList.add(user);
            }
        }

        return returnList;
    }

    @Override
    public List<UserDTO> readAllUsers() {
        return users;
    }

    @Override
    public UserDTO updateSingleUser(UserDTO user) {
        for(UserDTO u : users){
            if(u.getUserId() == user.getUserId()){
                return user;
            }
        }
        return null;
    }

    @Override
    public List<UserDTO> updateMultipleUsers(List<UserDTO> listOfUsers) {
        List<UserDTO> returnList = new ArrayList<>();

        for(UserDTO user : users){
            for(UserDTO i : listOfUsers){
                if(user.getUserId() == i.getUserId()){
                    returnList.add(user);
                }
            }
        }
        return returnList;
    }

    @Override
    public UserDTO setInactiveSingleUser(int userId) {
        return null;
    }

    @Override
    public UserDTO setInactiveMultipleUsers(List<Integer> listOfUserIds) {
        return null;
    }
}
