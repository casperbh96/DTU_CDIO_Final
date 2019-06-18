package main.java.DataAccess.test;

import main.java.Core.ProductBatchDTO;
import main.java.Core.REL_ProductBatchResourceBatchDTO;
import main.java.Core.ResourceBatchDTO;
import main.java.DataAccess.dao.DAO_ProductBatch;
import main.java.DataAccess.dao.DAO_REL_ProductBatchResourceBatch;
import main.java.DataAccess.dao.DAO_ResourceBatch;
import org.junit.jupiter.api.Assertions;

import java.sql.BatchUpdateException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DAO_ProductBatchResourceBatchTest {
    DAO_REL_ProductBatchResourceBatch dao = new DAO_REL_ProductBatchResourceBatch();
    DAO_ProductBatch daoProdBatch = new DAO_ProductBatch();
    DAO_ResourceBatch daoResBatch = new DAO_ResourceBatch();

    // region CREATE

    @org.junit.Test
    public void createMultipleProductBatchesResourceBatchesTest() {
        try {
            List<REL_ProductBatchResourceBatchDTO> relProductBatchResourceBatchDTOS = new ArrayList<>();

            List<ProductBatchDTO> prodBatchList = new ArrayList<>();
            ProductBatchDTO productBatch1 = new ProductBatchDTO(980, Date.valueOf("9999-12-31"), "under produktion", Date.valueOf("2000-12-31"),false, 60, Date.valueOf("9999-12-31"), 61);
            ProductBatchDTO productBatch2 = new ProductBatchDTO(981, Date.valueOf("9999-12-31"), "under produktion", Date.valueOf("2000-12-31"), false,61, Date.valueOf("9999-12-31"), 61);
            prodBatchList.add(productBatch1);
            prodBatchList.add(productBatch2);

            List<ResourceBatchDTO> resBatchList = new ArrayList<>();
            ResourceBatchDTO resBatch1 = new ResourceBatchDTO(980, 123, "sup1", false, 60);
            ResourceBatchDTO resBatch2 = new ResourceBatchDTO(981, 456, "sup2", false, 61);
            resBatchList.add(resBatch1);
            resBatchList.add(resBatch2);

            assertEquals(prodBatchList.size(), resBatchList.size());

            for(int i = 0; i < prodBatchList.size() && i < resBatchList.size(); i++){
                relProductBatchResourceBatchDTOS.add(new REL_ProductBatchResourceBatchDTO(
                        resBatchList.get(i).getResourceBatchId(), prodBatchList.get(i).getProductBatchId(),
                        123,45));
            }

            daoProdBatch.createMultipleProductBatchs(prodBatchList);
            daoResBatch.createMultipleResourceBatchs(resBatchList);

            List<REL_ProductBatchResourceBatchDTO> returnList = dao.createMultipleProductBatchResourceBatchs(relProductBatchResourceBatchDTOS);
            assertNotNull(returnList);
            assertNotEquals(returnList.isEmpty(), returnList);

            for(REL_ProductBatchResourceBatchDTO i : returnList){
                System.out.println(i);
            }
        }
        catch (BatchUpdateException batchEx) {
            batchEx.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }



    // endregion

    // region READ
    @org.junit.Test
    public void readAllProductBatchesResourceBatchesTest() {
        try {
            List<REL_ProductBatchResourceBatchDTO> pbrbDTO = dao.readAllProductBatchResourceBatchs();
            assertNotNull(pbrbDTO);
            assertNotEquals(pbrbDTO.isEmpty(), pbrbDTO);

            for (REL_ProductBatchResourceBatchDTO i : pbrbDTO) {
                System.out.println(i);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @org.junit.Test
    public void readProductBatchResourceBatchBySearchTest() {
        try {
            List<REL_ProductBatchResourceBatchDTO> dtoList = dao.readProductBatchResourceBatchbySearch("81");
            assertNotNull(dtoList);
            assertNotEquals(dtoList.isEmpty(), dtoList);

            for (REL_ProductBatchResourceBatchDTO dto : dtoList) {
                System.out.println(dto);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @org.junit.Test
    public void readSingleProductBatchResourceBatchByIdTest() {
        try {
            REL_ProductBatchResourceBatchDTO res = dao.readSingleProductBatchResourceBatchbyId(980, 980);
            Assertions.assertNotNull(res);

            System.out.println(res);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    @org.junit.Test
    public void readMultipleProductBatchResourceBatchByListTest() {
        try {
            List<REL_ProductBatchResourceBatchDTO> userList = dao.readMultipleProductBatchResourceBatchsByList(
                    new ArrayList<>(Arrays.asList(980, 981)), new ArrayList<>(Arrays.asList(980, 981)));

            Assertions.assertNotNull(userList);
            Assertions.assertNotEquals(userList.isEmpty(), userList);

            for (REL_ProductBatchResourceBatchDTO i : userList) {
                System.out.println(i);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    // endregion

    // region UPDATE
    @org.junit.Test
    public void updateSingleRecipeResourceTest(){
        try{
            REL_ProductBatchResourceBatchDTO prodBatchResBatch = dao.updateSingleProductBatchResourceBatch(
                    new REL_ProductBatchResourceBatchDTO(980, 980, 33.33, 2.5));
            Assertions.assertNotNull(prodBatchResBatch);

            System.out.println(prodBatchResBatch);
        } catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    @org.junit.Test
    public void updateMultipleRecipeResourceTest(){
        try{
            List<REL_ProductBatchResourceBatchDTO> prodBatchResBatchList = new ArrayList<>();
            REL_ProductBatchResourceBatchDTO pbrb1 = new REL_ProductBatchResourceBatchDTO(980, 980, 55.55, 3.5);
            REL_ProductBatchResourceBatchDTO pbrb2 = new REL_ProductBatchResourceBatchDTO(981, 981, 56.56, 4.5);
            prodBatchResBatchList.add(pbrb1);
            prodBatchResBatchList.add(pbrb2);

            List<REL_ProductBatchResourceBatchDTO> allRes = dao.updateMultipleProductBatchResourceBatchs(prodBatchResBatchList);
            Assertions.assertNotNull(allRes);
            Assertions.assertNotEquals(prodBatchResBatchList.isEmpty(), prodBatchResBatchList);

            for (REL_ProductBatchResourceBatchDTO i : allRes) {
                System.out.println(i);
            }
        } catch (SQLException ex){
            ex.printStackTrace();
        }
    }
    // endregion

    // region DELETE

    @org.junit.Test
    public void deleteSingleRecipeResourceTest(){
        try{
            dao.deleteSingleProductBatchResourceBatch(180, 180);

        } catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    @org.junit.Test
    public void deleteMultipleRecipeResourceTest(){
        try{
            List<Integer> resourcesIds = new ArrayList<>(Arrays.asList(980, 981));
            List<Integer> recipeIds = new ArrayList<>(Arrays.asList(980, 981));

            dao.deleteMultipleProductBatchResourceBatchs(resourcesIds, recipeIds);
        } catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    // endregion

}
