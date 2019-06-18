package main.java.DataAccess.test;

import junit.framework.TestCase;
import main.java.Core.ProductBatchDTO;
import main.java.Core.REL_UserProductBatchDTO;
import main.java.Core.UserDTO;
import main.java.DataAccess.dao.DAO_ProductBatch;
import main.java.DataAccess.dao.DAO_REL_UserProductBatch;
import main.java.DataAccess.dao.DAO_User;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;

import java.sql.BatchUpdateException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DAO_UserProductBatch {
    DAO_REL_UserProductBatch dao = new DAO_REL_UserProductBatch();
    DAO_User daoUser = new DAO_User();
    DAO_ProductBatch daoPB = new DAO_ProductBatch();

    // region CREATE
    @org.junit.Test
    public void createSingleUserProductBatchTest(){
        try{
            boolean returnBool = dao.assignSingleProductBatchUser(
                    new REL_UserProductBatchDTO(558, 601));
            Assertions.assertEquals(true, returnBool);

        } catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    @org.junit.Test
    public void createMultipleUserProductBatchesTest() {
        try{
            List<REL_UserProductBatchDTO> upbDTO = new ArrayList<>();

            UserDTO user = new UserDTO(563, "CapTest9", "CT9", false);

            List<ProductBatchDTO> pbList = new ArrayList<>();
            ProductBatchDTO pb1 = new ProductBatchDTO(600, Date.valueOf("9999-12-31"),
                    "Done", Date.valueOf("9999-12-31"), false, 65,
                    Date.valueOf("9999-12-31"), 563);

            ProductBatchDTO pb2 = new ProductBatchDTO(601, Date.valueOf("9999-12-31"),
                    "Done", Date.valueOf("9999-12-31"), false, 66,
                    Date.valueOf("9999-12-31"), 563);
            pbList.add(pb1);
            pbList.add(pb2);

            for(ProductBatchDTO pb : pbList){
                upbDTO.add(new REL_UserProductBatchDTO(user.getUserId(), pb.getProductBatchId()));
            }

            daoUser.createSingleUser(user);
            daoPB.createMultipleProductBatchs(pbList);

            boolean returnBool = dao.assignMultipleProductBatchUsers(upbDTO);
            assertEquals(true, returnBool);
        } catch (BatchUpdateException batchEx) {
            batchEx.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    // endregion

    // region READ
    @org.junit.Test
    public void readAllUserProductBatchesTest() {
        try {
            List<REL_UserProductBatchDTO> upbDTO = dao.readAllProductBatchUsers();
            assertNotNull(upbDTO);
            assertNotEquals(upbDTO.isEmpty(), upbDTO);


            for (REL_UserProductBatchDTO i : upbDTO) {
                System.out.println(i);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @org.junit.Test
    public void readUserProductBatchesByProductBatchIdTest() {
        try {
            List<REL_UserProductBatchDTO> dtoList = dao.readProductBatchUsers(503);
            TestCase.assertNotNull(dtoList);
            Assert.assertNotEquals(dtoList.isEmpty(), dtoList);

            for (REL_UserProductBatchDTO dto : dtoList) {
                System.out.println(dto);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @org.junit.Test
    public void readUserProductBatchesByUserIdTest() {
        try {
            List<REL_UserProductBatchDTO> dtoList = dao.readProductBatchesByUserId(560);
            TestCase.assertNotNull(dtoList);
            Assert.assertNotEquals(dtoList.isEmpty(), dtoList);

            for (REL_UserProductBatchDTO dto : dtoList) {
                System.out.println(dto);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    // endregion

    // region UPDATE

    // endregion

    // region DELETE
    @org.junit.Test
    public void deleteUserProductBatchesByProductBatchIdTest() {
        try {
            dao.deleteSingleUserProductBatch(559, 503);
            List<REL_UserProductBatchDTO> dtoList = dao.readAllProductBatchUsers();

            for (REL_UserProductBatchDTO dto : dtoList) {
                System.out.println(dto);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    // endregion

}
