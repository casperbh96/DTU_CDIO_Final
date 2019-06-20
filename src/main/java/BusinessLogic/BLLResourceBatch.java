package main.java.BusinessLogic;

import main.java.Core.ResourceBatchDTO;
import main.java.DataAccess.dao.DAO_ResourceBatch;
import main.java.DataAccess.dao.I_DAL_ResourceBatch;

import java.sql.SQLException;
import java.util.List;

public class BLLResourceBatch implements I_BLLResourceBatch {
    I_DAL_ResourceBatch dao = new DAO_ResourceBatch();
    @Override
    public ResourceBatchDTO createSingleResourceBatch(ResourceBatchDTO singleResourceBatch) throws SQLException {
        return null;
    }

    @Override
    public List<ResourceBatchDTO> createMultipleResourceBatchs(List<ResourceBatchDTO> listOfResourceBatchs) throws SQLException {
        return null;
    }

    @Override
    public ResourceBatchDTO readSingleResourceBatchById(int resourceBatchId) throws SQLException {
        return null;
    }

    @Override
    public List<ResourceBatchDTO> readMultipleResourceBatchsByList(List<Integer> listOfResourceBatchIds) throws SQLException {
        return null;
    }

    @Override
    public List<ResourceBatchDTO> readResourceBatchBySearch(String keyword) throws SQLException {
        return null;
    }

    @Override
    public List<ResourceBatchDTO> readAllResourceBatchs() throws SQLException {
        return dao.readAllResourceBatchs();
    }

    @Override
    public ResourceBatchDTO updateSingleResourceBatch(ResourceBatchDTO resourceBatch) throws SQLException {
        return null;
    }

    @Override
    public List<ResourceBatchDTO> updateMultipleResourceBatchs(List<ResourceBatchDTO> listOfResourceBatchs) throws SQLException {
        return null;
    }

    @Override
    public ResourceBatchDTO setIsLeftoverSingleResourceBatch(int resourceBatchId) throws SQLException {
        return null;
    }

    @Override
    public List<ResourceBatchDTO> setIsLeftoverMultipleResourceBatchs(List<Integer> listOfResourceBatchIds) throws SQLException {
        return null;
    }
}
