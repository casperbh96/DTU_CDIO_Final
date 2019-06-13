package main.java.DataAccess.dao;

import main.java.Core.ResourceBatchDTO;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

public interface I_DAL_ResourceBatch extends Serializable {

    ResourceBatchDTO createSingleResourceBatch(ResourceBatchDTO singleResourceBatch) throws SQLException;
    List<ResourceBatchDTO> createMultipleResourceBatchs( List<ResourceBatchDTO> listOfResourceBatchs ) throws SQLException;

    ResourceBatchDTO readSingleResourceBatchById(int resourceBatchId ) throws SQLException;
    List<ResourceBatchDTO> readMultipleResourceBatchsByList (List<Integer> listOfResourceBatchIds) throws SQLException;
    List<ResourceBatchDTO> readResourceBatchBySearch(String keyword) throws SQLException;
    List<ResourceBatchDTO> readAllResourceBatchs() throws SQLException;

    ResourceBatchDTO updateSingleResourceBatch(ResourceBatchDTO resourceBatch) throws SQLException;
    List<ResourceBatchDTO> updateMultipleResourceBatchs(List<ResourceBatchDTO> listOfResourceBatchs) throws SQLException;

    ResourceBatchDTO setIsLeftoverSingleResourceBatch(int resourceBatchId) throws SQLException;
    ResourceBatchDTO setIsLeftoverMultipleResourceBatchs(List<Integer> listOfResourceBatchIds) throws SQLException;

}
