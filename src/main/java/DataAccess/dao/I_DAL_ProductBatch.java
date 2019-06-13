package main.java.DataAccess.dao;

import main.java.Core.ProductBatchDTO;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

public interface I_DAL_ProductBatch extends Serializable {

    ProductBatchDTO createSingleProductBatch( ProductBatchDTO singleProductBatch ) throws SQLException;
    List<ProductBatchDTO> createMultipleProductBatchs( List<ProductBatchDTO> listOfProductBatchs ) throws SQLException;

    ProductBatchDTO readSingleProductBatchbyId(int productBatchId ) throws SQLException;
    List<ProductBatchDTO> readMultipleProductBatchsByList (List<Integer> listOfProductBatchIds) throws SQLException;
    List<ProductBatchDTO> readProductBatchbySearch(String keyword) throws SQLException;
    List<ProductBatchDTO> readAllProductBatchs() throws SQLException;

    ProductBatchDTO updateSingleProductBatch(ProductBatchDTO productBatch) throws SQLException;
    List<ProductBatchDTO> updateMultipleProductBatchs(List<ProductBatchDTO> listOfProductBatches) throws SQLException;

//    ProductBatchDTO deleteSingleProductBatch(ProductBatchDTO productBatch) throws SQLException;
//    List<ProductBatchDTO> deleteMultipleProductBatchs(List<ProductBatchDTO> listOfProductBatches) throws SQLException;

}
