package main.java.BusinessLogic;

import main.java.Core.ProductBatchDTO;

import java.sql.SQLException;
import java.util.List;

public interface I_BLLProductBatch {

    ProductBatchDTO createProductBatch(ProductBatchDTO singleProductBatch, List<Integer> listOfResourceBatchIds, List<Double> listOfNetAmounts, List<Integer> listOfTaras) throws SQLException;
//    List<ProductBatchDTO> createMultipleProductBatchs(List<ProductBatchDTO> listOfProductBatches ) throws SQLException;

    ProductBatchDTO getProductBatchById(int productBatchId ) throws SQLException;
    List<ProductBatchDTO> getProductBatchesByList (List<Integer> listOfProductBatchIds) throws SQLException;
    List<ProductBatchDTO> getProductBatchBySearch(String keyword) throws SQLException;
    List<ProductBatchDTO> getAllProductBatchs() throws SQLException;
    List<ProductBatchDTO> getAllProductBatchesUnderProduction() throws SQLException;
    List<ProductBatchDTO> getALlProductBatchesFinished() throws SQLException;

    ProductBatchDTO updateProductBatchButNotResourceBatches(ProductBatchDTO productBatchDTO) throws SQLException;
    ProductBatchDTO updateSingleProductBatch(ProductBatchDTO productBatch, List<Integer> resourceIds, List<Double> netAmounts, List<Double> taras) throws SQLException;
//    List<ProductBatchDTO> updateMultipleProductBatchs(List<ProductBatchDTO> listOfProductBatches) throws SQLException;

    ProductBatchDTO setInactiveSingleProductBatch(int productBatchId) throws SQLException;
    List<ProductBatchDTO> setInactiveMultipleProductBatchs(List<Integer> listOfProductBatchIds) throws SQLException;

}
