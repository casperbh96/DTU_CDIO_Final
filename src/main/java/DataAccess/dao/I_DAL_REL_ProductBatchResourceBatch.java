package main.java.DataAccess.dao;

import main.java.Core.REL_ProductBatchResourceBatchDTO;

import java.sql.SQLException;
import java.util.List;

public interface I_DAL_REL_ProductBatchResourceBatch {

    REL_ProductBatchResourceBatchDTO createSingleProductBatchResourceBatch(REL_ProductBatchResourceBatchDTO singleProductBatchResourceBatch) throws SQLException;
    List<REL_ProductBatchResourceBatchDTO> createMultipleProductBatchResourceBatchs(List<REL_ProductBatchResourceBatchDTO> listOfProductBatchResourceBatchs ) throws SQLException;

    REL_ProductBatchResourceBatchDTO readSingleProductBatchResourceBatchbyId(int resourceBatchId, int productBatchId) throws SQLException;
    List<REL_ProductBatchResourceBatchDTO> readMultipleProductBatchResourceBatchsByList(List<Integer> listOfResourceBatchIds, List<Integer> listOfProductBatchIds) throws SQLException;
    List<REL_ProductBatchResourceBatchDTO> readProductBatchResourceBatchbySearch(String keyword) throws SQLException;
    List<REL_ProductBatchResourceBatchDTO> readAllProductBatchResourceBatchs() throws SQLException;

    REL_ProductBatchResourceBatchDTO updateSingleProductBatchResourceBatch(REL_ProductBatchResourceBatchDTO productBatchResourceBatch) throws SQLException;
    List<REL_ProductBatchResourceBatchDTO> updateMultipleProductBatchResourceBatchs(List<REL_ProductBatchResourceBatchDTO> listOfProductBatchResourceBatchs) throws SQLException;

    void deleteSingleProductBatchResourceBatch(int resourceBatchId, int productBatchId) throws SQLException;
    void deleteMultipleProductBatchResourceBatchs(List<Integer> resourceBatchIds, List<Integer> productBatchIds) throws SQLException;
}
