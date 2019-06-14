package main.java.DataAccess.test;

import main.java.Core.ResourceDTO;
import main.java.Core.RoleDTO;
import main.java.DataAccess.dao.DAO_Role;

import javax.management.relation.Role;
import java.sql.BatchUpdateException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DAO_RoleTest {
    DAO_Role dao = new DAO_Role();

    @org.junit.Test
    public void readAllRolesTest() {
        try {
            List<RoleDTO> roleList = dao.readAllRoles();
            assertNotNull(roleList);
            assertNotEquals(roleList.isEmpty(), roleList);

            for (RoleDTO i : roleList) {
                System.out.println(i);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @org.junit.Test
    public void readRoleBySearchTest() {
        try {
            List<RoleDTO> resList = dao.readRoleBySearch("test");
            assertNotNull(resList);
            assertNotEquals(resList.isEmpty(), resList);

            for (RoleDTO res : resList) {
                System.out.println(res);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @org.junit.Test
    public void createMultipleRolesTest() {
        try {
            List<RoleDTO> List = new ArrayList<>();
            RoleDTO role = new RoleDTO(61, "123");
            RoleDTO role2 = new RoleDTO(62, "1234");
            List.add(role);
            List.add(role2);

            List<RoleDTO> allRoles = dao.createMultipleRoles(List);
            assertNotNull(allRoles);
            assertNotEquals(List.isEmpty(), List);

            for (RoleDTO i : allRoles) {
                System.out.println(i);
            }
        } catch (BatchUpdateException batchEx) {
            batchEx.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @org.junit.Test
    public void readSingleRoleByIdTest() {
        try {
            RoleDTO role = dao.readSingleRoleById(61);
            assertNotNull(role);

            System.out.println(role);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @org.junit.Test
    public void readMultipleResourcesByListTest() {
        try {
            List<RoleDTO> roleList = dao.readMultipleRolesByList(new ArrayList<>(Arrays.asList(62, 61)));
            assertNotNull(roleList);
            assertNotEquals(roleList.isEmpty(), roleList);

            for (RoleDTO i : roleList) {
                System.out.println(i);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @org.junit.Test
    public void updateSingleResourceTest(){
        try{
            RoleDTO res = dao.updateSingleRole(new RoleDTO(61, "testing1234"));
            assertNotNull(res);

            System.out.println(res);
        } catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    @org.junit.Test
    public void updateMultipleResourcesTest(){
        try{
            List<RoleDTO> roleList = new ArrayList<>();
            RoleDTO role1 = new RoleDTO(61, "1testworks");
            RoleDTO role2 = new RoleDTO(62, "2testworks");

            roleList.add(role1);
            roleList.add(role2);

            List<RoleDTO> allRole = dao.updateMultipleRoles(roleList);
            assertNotNull(allRole);
            assertNotEquals(roleList.isEmpty(), roleList);

            for (RoleDTO i : allRole) {
                System.out.println(i);
            }
        } catch (SQLException ex){
            ex.printStackTrace();
        }
    }


}
