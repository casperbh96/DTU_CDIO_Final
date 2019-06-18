package main.java.DataAccess.test;

import main.java.DataAccess.dao.DAO_Resource;
import main.java.Core.ResourceDTO;

import java.sql.BatchUpdateException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DAO_ResourceTest {
    DAO_Resource dao = new DAO_Resource();

    // region CREATE
    @org.junit.Test
    public void createMultipleResourcesTest() {
        try {
            List<ResourceDTO> resList = new ArrayList<>();
            ResourceDTO res1 = new ResourceDTO(61, "123", false, false);
            ResourceDTO res2 = new ResourceDTO(62, "1234", false, false);

            resList.add(res1);
            resList.add(res2);

            List<ResourceDTO> allRes = dao.createMultipleResources(resList);
            assertNotNull(allRes);
            assertNotEquals(resList.isEmpty(), resList);

            for (ResourceDTO i : allRes) {
                System.out.println(i);
            }
        } catch (BatchUpdateException batchEx) {
            batchEx.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    // endregion

    // region READ
    @org.junit.Test
    public void readAllResourcesTest() {
        try {
            List<ResourceDTO> resList = dao.readAllResources();
            assertNotNull(resList);
            assertNotEquals(resList.isEmpty(), resList);

            for (ResourceDTO i : resList) {
                System.out.println(i);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @org.junit.Test
    public void readResourcebySearchTest() {
        try {
            List<ResourceDTO> resList = dao.readResourcebySearch("test");
            assertNotNull(resList);
            assertNotEquals(resList.isEmpty(), resList);

            for (ResourceDTO res : resList) {
                System.out.println(res);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @org.junit.Test
    public void readSingleResourcebyIdTest() {
        try {
            ResourceDTO res = dao.readSingleResourcebyId(61);
            assertNotNull(res);

            System.out.println(res);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @org.junit.Test
    public void readMultipleResourcesByListTest() {
        try {
            List<ResourceDTO> resList = dao.readMultipleResourcesByList(new ArrayList<>(Arrays.asList(60, 61)));
            assertNotNull(resList);
            assertNotEquals(resList.isEmpty(), resList);

            for (ResourceDTO i : resList) {
                System.out.println(i);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    // endregion

    // region UPDATE
    @org.junit.Test
    public void updateSingleResourceTest(){
        try{
            ResourceDTO res = dao.updateSingleResource(new ResourceDTO(998, "testing1234", false, false));
            assertNotNull(res);

            System.out.println(res);
        } catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    @org.junit.Test
    public void updateMultipleResourcesTest(){
        try{
            List<ResourceDTO> resList = new ArrayList<>();
            ResourceDTO res1 = new ResourceDTO(64, "1testworks", false, false);
            ResourceDTO res2 = new ResourceDTO(65, "2testworks", false, false);

            resList.add(res1);
            resList.add(res2);

            List<ResourceDTO> allRes = dao.updateMultipleResources(resList);
            assertNotNull(allRes);
            assertNotEquals(resList.isEmpty(), resList);

            for (ResourceDTO i : allRes) {
                System.out.println(i);
            }
        } catch (SQLException ex){
            ex.printStackTrace();
        }
    }
    // endregion

    // region DELETE

    // endregion
}