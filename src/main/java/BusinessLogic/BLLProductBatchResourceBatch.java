package main.java.BusinessLogic;

import main.java.Core.REL_ProductBatchResourceBatchDTO;
import main.java.DataAccess.dao.DAO_REL_ProductBatchResourceBatch;
import main.java.DataAccess.dao.I_DAL_REL_ProductBatchResourceBatch;

import java.sql.SQLException;
import java.util.List;


public class BLLProductBatchResourceBatch implements I_BLLProductBatchResourceBatch {
    private DAO_REL_ProductBatchResourceBatch daoProdBatchResBatch = new DAO_REL_ProductBatchResourceBatch();

    @Override
    public REL_ProductBatchResourceBatchDTO createSingleProductBatchResourceBatch(REL_ProductBatchResourceBatchDTO singleProductBatchResourceBatch) throws SQLException {
        return daoProdBatchResBatch.createSingleProductBatchResourceBatch(singleProductBatchResourceBatch);
    }

    @Override
    public List<REL_ProductBatchResourceBatchDTO> createMultipleProductBatchResourceBatchs(List<REL_ProductBatchResourceBatchDTO> listOfProductBatchResourceBatchs) throws SQLException {
        return daoProdBatchResBatch.createMultipleProductBatchResourceBatchs(listOfProductBatchResourceBatchs);
    }

    @Override
    public REL_ProductBatchResourceBatchDTO readSingleProductBatchResourceBatchbyId(int resourceBatchId, int productBatchId) throws SQLException {
        return daoProdBatchResBatch.readSingleProductBatchResourceBatchbyId(resourceBatchId, productBatchId);
    }

    @Override
    public List<REL_ProductBatchResourceBatchDTO> readMultipleProductBatchResourceBatchsByList(List<Integer> listOfResourceBatchIds, List<Integer> listOfProductBatchIds) throws SQLException {
        return daoProdBatchResBatch.readMultipleProductBatchResourceBatchsByList(listOfResourceBatchIds, listOfProductBatchIds);
    }

    @Override
    public List<REL_ProductBatchResourceBatchDTO> readProductBatchResourceBatchbySearch(String keyword) throws SQLException {
        return daoProdBatchResBatch.readProductBatchResourceBatchbySearch(keyword);
    }

    @Override
    public List<REL_ProductBatchResourceBatchDTO> readAllProductBatchResourceBatchs() throws SQLException {
        return daoProdBatchResBatch.readAllProductBatchResourceBatchs();
    }

    @Override
    public REL_ProductBatchResourceBatchDTO updateSingleProductBatchResourceBatch(REL_ProductBatchResourceBatchDTO productBatchResourceBatch) throws SQLException {
        return daoProdBatchResBatch.updateSingleProductBatchResourceBatch(productBatchResourceBatch);
    }

    @Override
    public List<REL_ProductBatchResourceBatchDTO> updateMultipleProductBatchResourceBatchs(List<REL_ProductBatchResourceBatchDTO> listOfProductBatchResourceBatchs) throws SQLException {
        return daoProdBatchResBatch.updateMultipleProductBatchResourceBatchs(listOfProductBatchResourceBatchs);
    }

    @Override
    public void deleteSingleProductBatchResourceBatch(int resourceBatchId, int productBatchId) throws SQLException {
        daoProdBatchResBatch.deleteSingleProductBatchResourceBatch(resourceBatchId, productBatchId);
    }

    @Override
    public void deleteMultipleProductBatchResourceBatchs(List<Integer> resourceBatchIds, List<Integer> productBatchIds) throws SQLException {
        daoProdBatchResBatch.deleteMultipleProductBatchResourceBatchs(resourceBatchIds, productBatchIds);
    }
}
