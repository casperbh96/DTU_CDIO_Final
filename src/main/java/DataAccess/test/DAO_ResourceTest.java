package DataAccess.test;

import DataAccess.dao.DAO_Resource;
import DataAccess.dto.ResourceDTO;

import java.sql.BatchUpdateException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DAO_ResourceTest {
    DAO_Resource dao = new DAO_Resource();

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
    public void createMultipleResourcesTest() {
        try {
            List<ResourceDTO> resList = new ArrayList<>();
            ResourceDTO res1 = new ResourceDTO(60, "123", false);
            ResourceDTO res2 = new ResourceDTO(61, "1234", false);

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

    @org.junit.Test
    public void readSingleResourcebyIdTest() {
        try {
            ResourceDTO res = dao.readSingleResourcebyId(998);
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

    @org.junit.Test
    public void updateSingleResourceTest(){
        try{
            ResourceDTO res = dao.updateSingleResource(new ResourceDTO(998, "testing1234", false));
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
            ResourceDTO res1 = new ResourceDTO(60, "1testworks", false);
            ResourceDTO res2 = new ResourceDTO(61, "2testworks", false);

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

//    @org.junit.Test
//    public void deleteSingleResourceTest(){
//        try{
//            dao.deleteSingleResource(997);
//            assertThrows(SQLException.class, () -> dao.readSingleResourcebyId(997));
//
//        } catch (SQLException ex){
//            ex.printStackTrace();
//        }
//    }
//
//    @org.junit.Test
//    public void deleteMultipleResourcesTest(){
//        try{
//            List<Integer> resList = new ArrayList<>(Arrays.asList(4, 5));
//            dao.deleteMultipleResources(resList);
//            assertThrows(SQLException.class, () -> dao.readMultipleResourcesByList(resList));
//
//        } catch (SQLException ex){
//            ex.printStackTrace();
//        }
//    }
}
