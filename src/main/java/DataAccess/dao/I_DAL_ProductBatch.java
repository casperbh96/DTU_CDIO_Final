package DataAccess.dao;

import DataAccess.dto.ProductBatchDTO;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

public interface I_DAL_ProductBatch extends Serializable {

    ProductBatchDTO createSingleProductBatch( ProductBatchDTO singleProductBatch ) throws SQLException;
    List<ProductBatchDTO> createMultipleProductBatchs( List<ProductBatchDTO> listOfProductBatchs ) throws SQLException;

    ProductBatchDTO readSingleProductBatchbyId(int ProductBatchId ) throws SQLException;
    List<ProductBatchDTO> readMultipleProductBatchsByList (List<Integer> listOfProductBatchIds) throws SQLException;
    List<ProductBatchDTO> readProductBatchbySearch(String keyword) throws SQLException;
    List<ProductBatchDTO> readAllProductBatchs() throws SQLException;

    ProductBatchDTO updateSingleProductBatch(ProductBatchDTO ProductBatch) throws SQLException;
    List<ProductBatchDTO> updateMultipleProductBatchs(List<ProductBatchDTO> listOfProductBatchs) throws SQLException;

    ProductBatchDTO deleteSingleProductBatch(ProductBatchDTO ProductBatch) throws SQLException;
    List<ProductBatchDTO> deleteMultipleProductBatchs(List<ProductBatchDTO> listOfProductBatches) throws SQLException;

}
