package main.java.DataAccess.test;

import main.java.Core.ProductBatchDTO;
import main.java.Core.StringToSqlDateConverter;
import main.java.DataAccess.dao.DAO_ProductBatch;

import java.sql.BatchUpdateException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DAO_ProductBatchTest {
    DAO_ProductBatch dao = new DAO_ProductBatch();

    // region CREATE
    @org.junit.Test
    public void createMultipleProductBatchesTest() {
        try {
            List<ProductBatchDTO> dtoList = new ArrayList<>();
            ProductBatchDTO dto1 = new ProductBatchDTO(70, Date.valueOf("2000-12-31"), "under produktion", Date.valueOf("2000-12-31"), false, 60, Date.valueOf("9999-12-31"), 61);
            ProductBatchDTO dto2 = new ProductBatchDTO(71, Date.valueOf("2000-12-31"), "under produktion", Date.valueOf("2000-12-31"), false, 61, Date.valueOf("9999-12-31"), 61);

            dtoList.add(dto1);
            dtoList.add(dto2);

            List<ProductBatchDTO> allObjs = dao.createMultipleProductBatchs(dtoList);
            assertNotNull(allObjs);
            assertNotEquals(dtoList.isEmpty(), dtoList);

            for (ProductBatchDTO i : allObjs) {
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
        public void readAllProductBatchesTest () {
            try {
                List<ProductBatchDTO> pbDTO = dao.readAllProductBatchs();
                assertNotNull(pbDTO);
                assertNotEquals(pbDTO.isEmpty(), pbDTO);

                for (ProductBatchDTO i : pbDTO) {
                    System.out.println(i);
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

    @org.junit.Test
    public void readProductBatchBySearchTest() {
        try {
            List<ProductBatchDTO> dtoList = dao.readProductBatchBySearch("61");
            assertNotNull(dtoList);
            assertNotEquals(dtoList.isEmpty(), dtoList);

            for (ProductBatchDTO dto : dtoList) {
                System.out.println(dto);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @org.junit.Test
    public void readSingleProductBatchByIdTest() {
        try {
            ProductBatchDTO dto = dao.readSingleProductBatchById(1);
            assertNotNull(dto);

            System.out.println(dto);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @org.junit.Test
    public void readMultipleProductBatchesByListTest() {
        try {
            ArrayList<Integer> idList = new ArrayList<>();
            idList.add(70);
            idList.add(71);

            List<ProductBatchDTO> dtoList = dao.readMultipleProductBatchsByList(idList);
            assertNotNull(dtoList);
            assertNotEquals(dtoList.isEmpty(), dtoList);

            for (ProductBatchDTO i : dtoList) {
                System.out.println(i);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
        // endregion

        // region UPDATE
        @org.junit.Test
        public void updateSingleProductBatchTest(){
            try{
                ProductBatchDTO dto = dao.updateSingleProductBatch(new ProductBatchDTO(2, Date.valueOf("9999-12-30"), "afsluttet", Date.valueOf("9999-12-30"), false, 2, new StringToSqlDateConverter().convertStringToDate("2019-09-29"), 1));
                assertNotNull(dto);

                System.out.println(dto);
            } catch (SQLException ex){
                ex.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

    @org.junit.Test
    public void updateMultipleResourceBatchesTest(){
        try{
            List<ProductBatchDTO> dtoList = new ArrayList<>();
            ProductBatchDTO obj1 = new ProductBatchDTO(70, Date.valueOf("2000-12-31"), "afsluttet", Date.valueOf("2000-12-31"), false, 60, Date.valueOf("9999-12-31"), 61);
            ProductBatchDTO obj2 = new ProductBatchDTO(71, Date.valueOf("2000-12-31"), "afsluttet", Date.valueOf("2000-12-31"), false, 61, Date.valueOf("9999-12-31"), 61);

            dtoList.add(obj1);
            dtoList.add(obj2);

            List<ProductBatchDTO> allDTOs = dao.updateMultipleProductBatchs(dtoList);
            assertNotNull(allDTOs);
            assertNotEquals(dtoList.isEmpty(), dtoList);

            for (ProductBatchDTO i : allDTOs) {
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
