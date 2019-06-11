package DataAccess.dao;

import DataAccess.dto.ResourceBatchDTO;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

public interface I_DAL_ResourceBatch extends Serializable {

    ResourceBatchDTO createSingleResourceBatch( ResourceBatchDTO singleResourceBatch ) throws SQLException;
    List<ResourceBatchDTO> createMultipleResourceBatchs( List<ResourceBatchDTO> listOfResourceBatchs ) throws SQLException;

    ResourceBatchDTO readSingleResourceBatchbyId(int ResourceBatchId ) throws SQLException;
    List<ResourceBatchDTO> readMultipleResourceBatchsByList (List<Integer> listOfResourceBatchIds) throws SQLException;
    List<ResourceBatchDTO> readResourceBatchbySearch(String keyword) throws SQLException;
    List<ResourceBatchDTO> readAllResourceBatchs() throws SQLException;

    ResourceBatchDTO updateSingleResourceBatch(ResourceBatchDTO ResourceBatch) throws SQLException;
    List<ResourceBatchDTO> updateMultipleResourceBatchs(List<ResourceBatchDTO> listOfResourceBatchs) throws SQLException;

    ResourceBatchDTO deleteSingleResourceBatch(ResourceBatchDTO ResourceBatch) throws SQLException;
    List<ResourceBatchDTO> deleteMultipleResourceBatchs(List<ResourceBatchDTO> listOfResourceBatchs) throws SQLException;

}
