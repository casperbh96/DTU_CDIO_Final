package DataAccess.dao;

import DataAccess.dto.ResourceBatchDTO;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

import static DataAccess.dao.Connector.static_commitTransAction;
import static DataAccess.dao.Connector.static_createConnection;
import static DataAccess.dao.Connector.static_startTransAction;
import static DataAccess.dao.Connector.*;

public class DAO_ResourceBatch implements main.java.dal.I_DAL_ResourceBatch {
    @Override
    public ResourceBatchDTO createSingleResourceBatch(ResourceBatchDTO singleResourceBatch) {
        return null;
    }

    @Override
    public List<ResourceBatchDTO> createMultipleResourceBatchs(List<ResourceBatchDTO> listOfResourceBatchs) {
        return null;
    }

    @Override
    public ResourceBatchDTO readSingleResourceBatchbyId(int ResourceBatchId) {
        return null;
    }

    @Override
    public List<ResourceBatchDTO> readMultipleResourceBatchsByList(List<Integer> listOfResourceBatchIds) {
        return null;
    }

    @Override
    public List<ResourceBatchDTO> readResourceBatchbySearch(String keyword) {
        return null;
    }

    @Override
    public List<ResourceBatchDTO> readAllResourceBatchs() {
        return null;
    }

    @Override
    public ResourceBatchDTO updateSingleResourceBatch(ResourceBatchDTO ResourceBatch) {
        return null;
    }

    @Override
    public List<ResourceBatchDTO> updateMultipleResourceBatchs(List<ResourceBatchDTO> listOfResourceBatchs) {
        return null;
    }

    @Override
    public ResourceBatchDTO deleteSingleResourceBatch(ResourceBatchDTO ResourceBatch) {
        return null;
    }

    @Override
    public List<ResourceBatchDTO> deleteMultipleResourceBatchs(List<ResourceBatchDTO> listOfResourceBatchs) {
        return null;
    }
}
