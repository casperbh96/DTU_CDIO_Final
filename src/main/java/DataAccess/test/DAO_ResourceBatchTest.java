package main.java.DataAccess.test;

import main.java.Core.ResourceBatchDTO;
import main.java.DataAccess.dao.DAO_ResourceBatch;
import org.junit.Test;

import java.sql.BatchUpdateException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertNotEquals;

public class DAO_ResourceBatchTest {
    DAO_ResourceBatch dao = new DAO_ResourceBatch();

    // region CREATE
    @org.junit.Test
    public void createMultipleResourceBatchesTest() {
        try {
            List<ResourceBatchDTO> dtoList = new ArrayList<>();
            ResourceBatchDTO res1 = new ResourceBatchDTO(65, 123, "sup1", false,61);
            ResourceBatchDTO res2 = new ResourceBatchDTO(66, 1234.5, "sup2", false, 61);

            dtoList.add(res1);
            dtoList.add(res2);

            List<ResourceBatchDTO> allRes = dao.createMultipleResourceBatchs(dtoList);
            assertNotNull(allRes);
            assertNotEquals(dtoList.isEmpty(), dtoList);

            for (ResourceBatchDTO i : allRes) {
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
    public void readAllResourceBatchesTest() {
        try {
            List<ResourceBatchDTO> dtoList = dao.readAllResourceBatchs();
            assertNotNull(dtoList);
            assertNotEquals(dtoList.isEmpty(), dtoList);

            for (ResourceBatchDTO i : dtoList) {
                System.out.println(i);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @org.junit.Test
    public void readResourceBatchBySearchTest() {
        try {
            List<ResourceBatchDTO> dtoList = dao.readResourceBatchBySearch("test");
            assertNotNull(dtoList);
            assertNotEquals(dtoList.isEmpty(), dtoList);

            for (ResourceBatchDTO res : dtoList) {
                System.out.println(res);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @org.junit.Test
    public void readSingleResourceBatchByIdTest() {
        try {
            ResourceBatchDTO res = dao.readSingleResourceBatchById(65);
            assertNotNull(res);

            System.out.println(res);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @org.junit.Test
    public void readMultipleResourceBatchesByListTest() {
        try {
            List<ResourceBatchDTO> dtoList = dao.readMultipleResourceBatchsByList(new ArrayList<>(Arrays.asList(65, 66)));
            assertNotNull(dtoList);
            assertNotEquals(dtoList.isEmpty(), dtoList);

            for (ResourceBatchDTO i : dtoList) {
                System.out.println(i);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    // endregion

    // region UPDATE
    @org.junit.Test
    public void updateSingleResourceBatchTest(){
        try{
            ResourceBatchDTO dto = dao.updateSingleResourceBatch(new ResourceBatchDTO(65, 2222, "supUpdated", false, 61));
            assertNotNull(dto);

            System.out.println(dto);
        } catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    @Test
    public void updateMultipleResourceBatchesTest(){
        try{
            List<ResourceBatchDTO> dtoList = new ArrayList<>();
            ResourceBatchDTO obj1 = new ResourceBatchDTO(65, 555, "supUpdatedMult", false, 61);
            ResourceBatchDTO obj2 = new ResourceBatchDTO(66, 444, "supUpdatedMult", false,61);

            dtoList.add(obj1);
            dtoList.add(obj2);

            List<ResourceBatchDTO> allDTOs = dao.updateMultipleResourceBatchs(dtoList);
            assertNotNull(allDTOs);
            assertNotEquals(dtoList.isEmpty(), dtoList);

            for (ResourceBatchDTO i : allDTOs) {
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
