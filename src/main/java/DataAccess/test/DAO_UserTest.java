package main.java.DataAccess.test;

import main.java.DataAccess.dao.DAO_User;
import main.java.DataAccess.dto.UserDTO;

import java.sql.BatchUpdateException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DAO_UserTest {
    DAO_User dao = new DAO_User();

    @org.junit.Test
    public void readAllUsersTest() {
        try {
            List<UserDTO> userList = dao.readAllUsers();
            assertNotNull(userList);
            assertNotEquals(userList.isEmpty(), userList);

            for (UserDTO i : userList) {
                System.out.println(i);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @org.junit.Test
    public void readUserBySearchTest() {
        try {
            List<UserDTO> userList = dao.readUserbySearch("mulla");
            assertNotNull(userList);
            assertNotEquals(userList.isEmpty(), userList);

            for (UserDTO res : userList) {
                System.out.println(res);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @org.junit.Test
    public void createMultipleUsersTest() {
        try {
            List<UserDTO> userList = new ArrayList<>();
            UserDTO user1 = new UserDTO(60, "123", "in1", false);
            UserDTO user2 = new UserDTO(61, "1234", "in2", false);

            userList.add(user1);
            userList.add(user2);

            List<UserDTO> allRes = dao.createMultipleUsers(userList);
            assertNotNull(allRes);
            assertNotEquals(userList.isEmpty(), userList);

            for (UserDTO i : allRes) {
                System.out.println(i);
            }
        } catch (BatchUpdateException batchEx) {
            batchEx.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @org.junit.Test
    public void readSingleUserByIdTest() {
        try {
            UserDTO res = dao.readSingleUserbyId(60);
            assertNotNull(res);

            System.out.println(res);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @org.junit.Test
    public void readMultipleUsersByListTest() {
        try {
            List<UserDTO> userList = dao.readMultipleUsersByList(new ArrayList<>(Arrays.asList(60, 61)));
            assertNotNull(userList);
            assertNotEquals(userList.isEmpty(), userList);

            for (UserDTO i : userList) {
                System.out.println(i);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @org.junit.Test
    public void updateSingleUserTest(){
        try{
            UserDTO res = dao.updateSingleUser(new UserDTO(60, "testing1234", "works1", false));
            assertNotNull(res);

            System.out.println(res);
        } catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    @org.junit.Test
    public void updateMultipleUsersTest(){
        try{
            List<UserDTO> userList = new ArrayList<>();
            UserDTO user1 = new UserDTO(60, "testing1234", "2testworks", false);
            UserDTO user2 = new UserDTO(61, "testing12345", "3testworks", false);

            userList.add(user1);
            userList.add(user2);

            List<UserDTO> allRes = dao.updateMultipleUsers(userList);
            assertNotNull(allRes);
            assertNotEquals(userList.isEmpty(), userList);

            for (UserDTO i : allRes) {
                System.out.println(i);
            }
        } catch (SQLException ex){
            ex.printStackTrace();
        }
    }
}
