package main.java.DataAccess.dao;

import main.java.Core.REL_ProductBatchResourceBatchDTO;

import java.sql.SQLException;
import java.util.List;

public class DAO_REL_ProductBatchResourceBatch implements I_DAL_REL_ProductBatchResourceBatch {
    @Override
    public REL_ProductBatchResourceBatchDTO createSingleProductBatchResourceBatch(REL_ProductBatchResourceBatchDTO singleProductBatchResourceBatch) throws SQLException {
        return null;
    }

    @Override
    public List<REL_ProductBatchResourceBatchDTO> createMultipleProductBatchResourceBatchs(List<REL_ProductBatchResourceBatchDTO> listOfProductBatchResourceBatchs) throws SQLException {
        return null;
    }

    @Override
    public REL_ProductBatchResourceBatchDTO readSingleProductBatchResourceBatchbyId(int productBatchResourceBatchId) throws SQLException {
        return null;
    }

    @Override
    public List<REL_ProductBatchResourceBatchDTO> readMultipleProductBatchResourceBatchsByList(List<Integer> listOfProductBatchResourceBatchIds) throws SQLException {
        return null;
    }

    @Override
    public List<REL_ProductBatchResourceBatchDTO> readProductBatchResourceBatchbySearch(String keyword) throws SQLException {
        return null;
    }

    @Override
    public List<REL_ProductBatchResourceBatchDTO> readAllProductBatchResourceBatchs() throws SQLException {
        return null;
    }

    @Override
    public REL_ProductBatchResourceBatchDTO updateSingleProductBatchResourceBatch(REL_ProductBatchResourceBatchDTO productBatchResourceBatch) throws SQLException {
        return null;
    }

    @Override
    public List<REL_ProductBatchResourceBatchDTO> updateMultipleProductBatchResourceBatchs(List<REL_ProductBatchResourceBatchDTO> listOfProductBatchResourceBatchs) throws SQLException {
        return null;
    }

    @Override
    public REL_ProductBatchResourceBatchDTO deleteSingleProductBatchResourceBatch(int productBatchResourceBatchId) throws SQLException {
        return null;
    }

    @Override
    public REL_ProductBatchResourceBatchDTO deleteMultipleProductBatchResourceBatchs(List<Integer> listOfProductBatchResourceBatchIds) throws SQLException {
        return null;
    }
}
