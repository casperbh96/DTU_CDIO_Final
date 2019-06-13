package main.java.DataAccess.dao;

import main.java.Core.ResourceBatchDTO;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

public interface I_DAL_ResourceBatch {

    ResourceBatchDTO createSingleResourceBatch( ResourceBatchDTO singleResourceBatch ) throws SQLException;
    List<ResourceBatchDTO> createMultipleResourceBatchs( List<ResourceBatchDTO> listOfResourceBatchs ) throws SQLException;

    ResourceBatchDTO readSingleResourceBatchbyId(int resourceBatchId ) throws SQLException;
    List<ResourceBatchDTO> readMultipleResourceBatchsByList (List<Integer> listOfResourceBatchIds) throws SQLException;
    List<ResourceBatchDTO> readResourceBatchbySearch(String keyword) throws SQLException;
    List<ResourceBatchDTO> readAllResourceBatchs() throws SQLException;

    ResourceBatchDTO updateSingleResourceBatch(ResourceBatchDTO resourceBatch) throws SQLException;
    List<ResourceBatchDTO> updateMultipleResourceBatchs(List<ResourceBatchDTO> listOfResourceBatches) throws SQLException;

//    ResourceBatchDTO deleteSingleResourceBatch(ResourceBatchDTO ResourceBatch) throws SQLException;
//    List<ResourceBatchDTO> deleteMultipleResourceBatchs(List<ResourceBatchDTO> listOfResourceBatchs) throws SQLException;

}
