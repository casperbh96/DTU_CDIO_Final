package main.java.BusinessLogic;

import main.java.Core.ProductBatchDTO;

import java.sql.SQLException;
import java.util.List;

public interface I_BLLProductBatch {

    ProductBatchDTO createSingleProductBatch(ProductBatchDTO singleProductBatch) throws SQLException;
    List<ProductBatchDTO> createMultipleProductBatchs(List<ProductBatchDTO> listOfProductBatches ) throws SQLException;

    ProductBatchDTO readSingleProductBatchById(int productBatchId ) throws SQLException;
    List<ProductBatchDTO> readMultipleProductBatchsByList (List<Integer> listOfProductBatchIds) throws SQLException;
    List<ProductBatchDTO> readProductBatchBySearch(String keyword) throws SQLException;
    List<ProductBatchDTO> readAllProductBatchs() throws SQLException;

    ProductBatchDTO updateSingleProductBatch(ProductBatchDTO productBatch) throws SQLException;
    List<ProductBatchDTO> updateMultipleProductBatchs(List<ProductBatchDTO> listOfProductBatches) throws SQLException;

    ProductBatchDTO setInactiveSingleProductBatch(int productBatchId) throws SQLException;
    List<ProductBatchDTO> setInactiveMultipleProductBatchs(List<Integer> listOfProductBatchIds) throws SQLException;

}
