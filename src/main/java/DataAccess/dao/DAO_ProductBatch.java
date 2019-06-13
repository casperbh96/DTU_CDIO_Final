package main.java.DataAccess.dao;

import main.java.Core.ProductBatchDTO;

import java.sql.*;
import java.util.List;

public class DAO_ProductBatch implements I_DAL_ProductBatch {


    @Override
    public ProductBatchDTO createSingleProductBatch(ProductBatchDTO singleProductBatch) throws SQLException {
        return null;
    }

    @Override
    public List<ProductBatchDTO> createMultipleProductBatchs(List<ProductBatchDTO> listOfProductBatches) throws SQLException {
        return null;
    }

    @Override
    public ProductBatchDTO readSingleProductBatchById(int productBatchId) throws SQLException {
        return null;
    }

    @Override
    public List<ProductBatchDTO> readMultipleProductBatchsByList(List<Integer> listOfProductBatchIds) throws SQLException {
        return null;
    }

    @Override
    public List<ProductBatchDTO> readProductBatchBySearch(String keyword) throws SQLException {
        return null;
    }

    @Override
    public List<ProductBatchDTO> readAllProductBatchs() throws SQLException {
        return null;
    }

    @Override
    public ProductBatchDTO updateSingleProductBatch(ProductBatchDTO productBatch) throws SQLException {
        return null;
    }

    @Override
    public List<ProductBatchDTO> updateMultipleProductBatchs(List<ProductBatchDTO> listOfProductBatches) throws SQLException {
        return null;
    }

    @Override
    public ProductBatchDTO setInactiveSingleProductBatch(int productBatchId) throws SQLException {
        return null;
    }

    @Override
    public ProductBatchDTO setInactiveMultipleProductBatchs(List<Integer> listOfProductBatchIds) throws SQLException {
        return null;
    }
}
