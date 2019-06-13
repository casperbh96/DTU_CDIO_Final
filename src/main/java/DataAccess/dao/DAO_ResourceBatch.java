package main.java.DataAccess.dao;

import main.java.Core.ResourceBatchDTO;

import java.sql.*;
import java.util.List;

public class DAO_ResourceBatch implements I_DAL_ResourceBatch {

    @Override
    public ResourceBatchDTO createSingleResourceBatch(ResourceBatchDTO singleResourceBatch) throws SQLException {
        return null;
    }

    @Override
    public List<ResourceBatchDTO> createMultipleResourceBatchs(List<ResourceBatchDTO> listOfResourceBatchs) throws SQLException {
        return null;
    }

    @Override
    public ResourceBatchDTO readSingleResourceBatchbyId(int resourceBatchId) throws SQLException {
        return null;
    }

    @Override
    public List<ResourceBatchDTO> readMultipleResourceBatchsByList(List<Integer> listOfResourceBatchIds) throws SQLException {
        return null;
    }

    @Override
    public List<ResourceBatchDTO> readResourceBatchbySearch(String keyword) throws SQLException {
        return null;
    }

    @Override
    public List<ResourceBatchDTO> readAllResourceBatchs() throws SQLException {
        return null;
    }

    @Override
    public ResourceBatchDTO updateSingleResourceBatch(ResourceBatchDTO resourceBatch) throws SQLException {
        return null;
    }

    @Override
    public List<ResourceBatchDTO> updateMultipleResourceBatchs(List<ResourceBatchDTO> listOfResourceBatches) throws SQLException {
        return null;
    }
}
